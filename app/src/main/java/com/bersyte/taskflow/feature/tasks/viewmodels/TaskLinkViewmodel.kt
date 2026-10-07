package com.bersyte.taskflow.feature.tasks.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bersyte.taskflow.core.ui.UiState
import com.bersyte.taskflow.feature.tasks.data.models.TaskLink
import com.bersyte.taskflow.feature.tasks.data.repositories.task.ITaskRepository
import com.bersyte.taskflow.feature.tasks.data.repositories.taskLink.ITaskLinkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

@HiltViewModel
class TaskLinkViewmodel @Inject constructor(
    private val taskLinkRepository: ITaskLinkRepository,
    private val taskRepository: ITaskRepository
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
        }catch (e: CancellationException){
            throw e
        } catch (e: Exception) {
            Log.d("TaskLinkViewmodel", "Fetch task with taskLinks error: $e")
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
        }catch (e: CancellationException){
            throw e
        }catch (e: Exception){
            Log.d("TaskLinkViewmodel", "saving taskLink error - $e")
            _taskLinkState.update {
                it.copy(isLoading = false, error = e.message)
            }
            return@launch
        }
    }
}
