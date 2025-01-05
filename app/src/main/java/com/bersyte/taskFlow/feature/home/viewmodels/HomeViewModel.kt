package com.bersyte.taskFlow.feature.home.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bersyte.taskFlow.core.ui.UiState
import com.bersyte.taskFlow.feature.tasks.data.models.Task
import com.bersyte.taskFlow.feature.tasks.data.models.TaskPriority
import com.bersyte.taskFlow.feature.tasks.data.repositories.task.ITaskRepository
import com.bersyte.taskFlow.utils.TaskHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val taskRepository: ITaskRepository,
): ViewModel() {

    private val _homeState = MutableStateFlow(UiState<List<Task>>())
    val homeState = _homeState.asStateFlow()

    private val _homeUrgentTaskState = MutableStateFlow(UiState<List<Task>>())
    val homeUrgentTaskState = _homeUrgentTaskState.asStateFlow()

    init {
        getTasksExcludingHighPriority()
        getThreeUrgentTasks()
    }

    private fun getTasksExcludingHighPriority() = viewModelScope.launch {
        try {
            _homeState.update {it.copy(isLoading = true)}
            //
            taskRepository.getTasksExcludingHighPriority().collect{ tasks ->

                val result = tasks.map { taskWithSubtasks ->
                    val subtasks = taskWithSubtasks.subtasks

                    taskWithSubtasks.task.copy(
                        percentageCompleted = TaskHelper.percentageCompletedPerTask(subtasks),
                        isCompleted = subtasks.isNotEmpty() && subtasks.all { it.isCompleted }
                    )
                }

                _homeState.update {
                    it.copy(isLoading = false, data = result)
                }
            }
        }catch (e: Exception){
            val tag = "Error while getting all tasks"
            Log.d(tag, "$tag - ${e.message}")
            _homeState.update {
                it.copy(isLoading = false, error = e.message)
            }
            return@launch
        }
    }

    private fun getThreeUrgentTasks() = viewModelScope.launch {
        try {
            _homeUrgentTaskState.update {it.copy(isLoading = true)}
            //
            taskRepository.getTasksByPriority(TaskPriority.HIGH).collect{ tasks ->

                val result = tasks.map { taskWithSubtasks ->
                    val subtasks = taskWithSubtasks.subtasks

                    taskWithSubtasks.task.copy(
                        percentageCompleted = TaskHelper.percentageCompletedPerTask(subtasks),
                        isCompleted = subtasks.isNotEmpty() && subtasks.all { it.isCompleted }
                    )
                }

                _homeUrgentTaskState.update {
                    it.copy(isLoading = false, data = result.filter { task ->
                        !task.isCompleted
                    })
                }
            }
        }catch (e: Exception){
            val tag = "Error while getting all tasks"
            Log.d(tag, "$tag - ${e.message}")
            _homeUrgentTaskState.update {
                it.copy(isLoading = false, error = e.message)
            }
            return@launch
        }
    }
}
