package com.bersyte.taskflow.feature.tasks.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bersyte.taskflow.core.ui.UiState
import com.bersyte.taskflow.feature.notifications.data.models.AppNotification
import com.bersyte.taskflow.feature.notifications.data.repositories.INotificationRepository
import com.bersyte.taskflow.feature.notifications.service.INotificationService
import com.bersyte.taskflow.feature.tasks.data.models.Subtask
import com.bersyte.taskflow.feature.tasks.data.models.Task
import com.bersyte.taskflow.feature.tasks.data.repositories.subtask.ISubtaskRepository
import com.bersyte.taskflow.feature.tasks.data.repositories.task.ITaskRepository
import com.bersyte.taskflow.utils.AppHelper
import com.bersyte.taskflow.utils.TaskHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SubtaskViewmodel @Inject constructor(
    private val subtaskRepository: ISubtaskRepository,
    private val taskRepository: ITaskRepository,
    private val notificationsRepository: INotificationRepository,
    private val notificationService: INotificationService
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
        }catch (e: CancellationException){
            throw e
        } catch (e: Exception) {
            Log.d("SubtaskViewmodel", "Fetch task with subtasks exception: $e")
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
        }catch (e: CancellationException){
            throw e
        }catch (e: Exception){
            Log.d("SubtaskViewmodel", "Saving subtask exception- $e")
            _subtaskState.update {
                it.copy(isLoading = false, error = e.message)
            }
            return@launch
        }
    }

    fun deleteSubtask(subtask: Subtask)= viewModelScope.launch {
        try {
            subtaskRepository.delete(subtask)
        }catch (e: CancellationException){
            throw e
        }catch (e:Exception){
            Log.d("SubtaskViewmodel", "Delete subtask error: $e")
            _subtaskState.update {
                it.copy(isLoading = false, error = e.message)
            }
            return@launch
        }
    }

    fun updateSubtask(subtask: Subtask, taskId: Long)= viewModelScope.launch {
        try {
            subtaskRepository.update(subtask)

            //save and show notification
            saveNotification(taskId)

        }catch (e: CancellationException){
            throw e
        }catch (e:Exception){
            Log.d("SubtaskViewmodel", "Update subtask error: $e")
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

                     taskRepository.getTaskById(taskId).collect{ taskWithSub ->
                         if(taskWithSub != null){
                             val subtasks = taskWithSub.subtasks

                             val task = taskWithSub.task.copy(
                                 percentageCompleted = TaskHelper.percentageCompletedPerTask(subtasks),
                                 isCompleted = subtasks.isNotEmpty() && subtasks.all { it.isCompleted }
                             )

                             if(task.isCompleted){
                                 //show local notification
                                 showNotification(taskWithSub.task)
                             }
                         }
                     }

                 }else{
                     taskRepository.getTasksWithSubtasks(taskId).collect{ taskWithSubtasks ->
                         val subtasks = taskWithSubtasks.subtasks

                         val task = taskWithSubtasks.task.copy(
                             percentageCompleted = TaskHelper.percentageCompletedPerTask(subtasks),
                             isCompleted = subtasks.isNotEmpty() && subtasks.all { it.isCompleted }
                         )

                         if(task.isCompleted){
                             val notification = AppNotification(
                                 id = 0,
                                 title = task.title,
                                 taskId = taskId
                             )
                             notificationsRepository.insert(notification)

                             //show local notification
                             showNotification(task)
                         }
                     }
                 }
            }
        }catch (e: CancellationException){
            throw e
        } catch (e: Exception){
            Log.d("SubtaskViewmodel", "saving notification exception - ${e.message}")
            return@launch
        }
    }

    private fun showNotification(task: Task){
        notificationService.showNotification(
            title = task.title,
            description = "Task completed successfully 🎉",
            taskId = task.taskId.toInt()
        )
    }
}
