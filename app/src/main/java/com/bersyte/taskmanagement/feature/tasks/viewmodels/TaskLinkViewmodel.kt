package com.bersyte.taskmanagement.feature.tasks.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bersyte.taskmanagement.core.ui.UiState
import com.bersyte.taskmanagement.feature.tasks.data.models.TaskLink
import com.bersyte.taskmanagement.feature.tasks.data.repositories.task.TaskRepository
import com.bersyte.taskmanagement.feature.tasks.data.repositories.taskLink.TaskLinkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskLinkViewmodel @Inject constructor(
    private val taskLinkRepository: TaskLinkRepository,
    private val taskRepository: TaskRepository
): ViewModel() {

    private val _taskLinkState = MutableStateFlow(UiState<List<TaskLink>>())
    val taskLinkState = _taskLinkState.asStateFlow()


    fun getTaskWithLinks(taskId: Long) = viewModelScope.launch {
        _taskLinkState.update { it.copy(isLoading = true) }

        try {
            taskRepository.getTasksWithLinks(taskId).collect{ task ->
                _taskLinkState.update {
                    it.copy(isLoading = false, data = task.taskLinks)
                }
            }
        } catch (e: Exception) {
            Log.d("Fetch task with taskLinks", "Fetch task with taskLinks error: $e")
            _taskLinkState.update {
                it.copy(isLoading = false, error = e.message)
            }
        }
    }


    fun saveLink(link: String, taskOwnerId: Long) = viewModelScope.launch {
        try {
            _taskLinkState.update {it.copy(isLoading = true)}
            val taskLink = TaskLink(
                taskLinkId = 0,
                url = link,
                taskOwnerId = taskOwnerId
            )
            taskLinkRepository.insert(taskLink)
            //
            _taskLinkState.update {it.copy(isLoading = false)}
        }catch (e: Exception){
            val tag = "Error while saving taskLink"
            Log.d(tag, "$tag - ${e.message}")
            _taskLinkState.update {
                it.copy(isLoading = false, error = e.message)
            }
            return@launch
        }
    }

//    fun updateTaskLink(taskLink: TaskLink)= viewModelScope.launch {
//        try {
//            taskLinkRepository.update(taskLink)
//        }catch (e:Exception){
//            Log.d("Update taskLink", "Update taskLink error: $e")
//            _taskLinkState.update {
//                it.copy(isLoading = false, error = e.message)
//            }
//            return@launch
//        }
//    }
//
//    fun deleteTaskLink(taskLink: TaskLink)= viewModelScope.launch {
//        try {
//            taskLinkRepository.delete(taskLink)
//        }catch (e:Exception){
//            Log.d("Delete taskLink", "Delete taskLink error: $e")
//            _taskLinkState.update {
//                it.copy(isLoading = false, error = e.message)
//            }
//            return@launch
//        }
//    }
}
