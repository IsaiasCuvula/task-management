package com.bersyte.taskFlow.feature.home.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bersyte.taskFlow.core.ui.UiState
import com.bersyte.taskFlow.feature.tasks.data.models.Task
import com.bersyte.taskFlow.feature.tasks.data.repositories.task.TaskRepository
import com.bersyte.taskFlow.utils.TaskHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksCompletedViewModel @Inject constructor(
    private val taskRepository: TaskRepository,
): ViewModel() {

    private val _tasksCompletedState = MutableStateFlow(UiState<List<Task>>())
    val tasksCompletedState = _tasksCompletedState.asStateFlow()


    init {
        getAllTasks()
    }

    private fun getAllTasks() = viewModelScope.launch {
        try {
            _tasksCompletedState.update { it.copy(isLoading = true) }
            taskRepository.getAllTasksWithSubtasks().collect { tasks ->
                val result = tasks.map { taskWithSubtasks ->
                    val subtasks = taskWithSubtasks.subtasks

                    taskWithSubtasks.task.copy(
                        percentageCompleted = TaskHelper.percentageCompletedPerTask(subtasks),
                        isCompleted = subtasks.isNotEmpty() && subtasks.all { it.isCompleted }
                    )
                }
                _tasksCompletedState.update {
                    it.copy(isLoading = false, data = result)
                }
            }
        }catch (e: Exception){
            val tag = "TasksCompletedViewModel "
            Log.d(tag, "Error Get all tasks - ${e.message}")
            _tasksCompletedState.update {
                it.copy(isLoading = false, error = e.message)
            }
            return@launch
        }
    }
}
