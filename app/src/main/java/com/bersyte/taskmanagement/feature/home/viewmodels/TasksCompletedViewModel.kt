package com.bersyte.taskmanagement.feature.home.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bersyte.taskmanagement.core.ui.UiState
import com.bersyte.taskmanagement.feature.tasks.data.models.Task
import com.bersyte.taskmanagement.feature.tasks.data.repositories.task.TaskRepository
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
            // Collect tasks with subtasks
            taskRepository.getAllTasksWithSubtasks().collect { tasks ->
                val enrichedTasks = tasks.map { taskWithSubtasks ->
                    // Update task completion status based on subtasks
                    taskWithSubtasks.task.copy(
                        isCompleted = taskWithSubtasks.subtasks.all { it.isCompleted }
                    )
                }
                // Update state with enriched tasks
                _tasksCompletedState.update {
                    it.copy(isLoading = false, data = enrichedTasks)
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
