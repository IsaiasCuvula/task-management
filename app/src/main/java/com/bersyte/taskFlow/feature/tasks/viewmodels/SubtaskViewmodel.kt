package com.bersyte.taskFlow.feature.tasks.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bersyte.taskFlow.core.ui.UiState
import com.bersyte.taskFlow.feature.notifications.data.models.AppNotification
import com.bersyte.taskFlow.feature.notifications.data.repositories.NotificationRepository
import com.bersyte.taskFlow.feature.tasks.data.models.Subtask
import com.bersyte.taskFlow.feature.tasks.data.repositories.subtask.SubtaskRepository
import com.bersyte.taskFlow.feature.tasks.data.repositories.task.TaskRepository
import com.bersyte.taskFlow.utils.AppHelper
import com.bersyte.taskFlow.utils.TaskHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SubtaskViewmodel @Inject constructor(
    private val subtaskRepository: SubtaskRepository,
    private val taskRepository: TaskRepository,
    private val notificationsRepository: NotificationRepository
): ViewModel() {

    private val _subtaskState = MutableStateFlow(UiState<List<Subtask>>())
    val subtaskState = _subtaskState.asStateFlow()


    fun getTaskWithSubtasks(taskId: Long) = viewModelScope.launch {
        _subtaskState.update { it.copy(isLoading = true) }
        try {
            taskRepository.getTasksWithSubtasks(taskId).collect{ task ->
                _subtaskState.update {
                    it.copy(isLoading = false, data = task.subtasks)
                }
            }
        } catch (e: Exception) {
            Log.d("Fetch task with subtasks", "Fetch task with subtasks error: $e")
            _subtaskState.update {
                it.copy(isLoading = false, error = e.message)
            }
        }
    }


    fun saveSubtask(title: String, taskOwnerId: Long) = viewModelScope.launch {
        try {
            _subtaskState.update {it.copy(isLoading = true)}
            val subtask = Subtask(
                subtaskId = 0,
                title = title,
                taskOwnerId = taskOwnerId
            )
            subtaskRepository.insert(subtask)
            //
            _subtaskState.update {it.copy(isLoading = false)}
        }catch (e: Exception){
            val tag = "Error while saving subtask"
            Log.d(tag, "$tag - ${e.message}")
            _subtaskState.update {
                it.copy(isLoading = false, error = e.message)
            }
            return@launch
        }
    }

    fun deleteSubtask(subtask: Subtask)= viewModelScope.launch {
        try {
            subtaskRepository.delete(subtask)
        }catch (e:Exception){
            Log.d("Delete subtask", "Delete subtask error: $e")
            _subtaskState.update {
                it.copy(isLoading = false, error = e.message)
            }
            return@launch
        }
    }

    fun updateSubtask(subtask: Subtask, taskId: Long)= viewModelScope.launch {
        try {
            subtaskRepository.update(subtask)
            saveNotification(taskId)
        }catch (e:Exception){
            Log.d("Update subtask", "Update subtask error: $e")
            _subtaskState.update {
                it.copy(isLoading = false, error = e.message)
            }
            return@launch
        }
    }

    private fun saveNotification(taskId: Long) = viewModelScope.launch {
        try {
             notificationsRepository.notificationByTaskId(taskId).collect{ existingNotification ->

                 if(existingNotification != null){
                     val updatedNot = existingNotification.copy(
                         isSeen = false,
                         title = existingNotification.title,
                         createdAt = AppHelper.getCurrentDate()
                     )
                     notificationsRepository.update(updatedNot)
                 }else{
                     taskRepository.getTasksWithSubtasks(taskId).collect{ taskWithSubtasks ->
                         val subtasks = taskWithSubtasks.subtasks

                         val result = taskWithSubtasks.task.copy(
                             percentageCompleted = TaskHelper.percentageCompletedPerTask(subtasks),
                             isCompleted = subtasks.isNotEmpty() && subtasks.all { it.isCompleted }
                         )

                         if(result.isCompleted){
                             val notification = AppNotification(
                                 id = 0,
                                 title = taskWithSubtasks.task.title,
                                 taskId = taskId
                             )
                             notificationsRepository.insert(notification)
                         }
                     }
                 }
            }
        } catch (e: Exception){
            val tag = "Error while saving notification"
            Log.d(tag, "$tag - ${e.message}")
            return@launch
        }
    }
}
