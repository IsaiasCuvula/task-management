package com.bersyte.taskmanagement.feature.tasks.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bersyte.taskmanagement.core.ui.UiState
import com.bersyte.taskmanagement.feature.tasks.data.models.Task
import com.bersyte.taskmanagement.feature.tasks.data.repositories.task.TaskRepository
import com.bersyte.taskmanagement.utils.TaskHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TaskViewmodel @Inject constructor(
    private val repository: TaskRepository
): ViewModel() {

    private val _taskState = MutableStateFlow(UiState<List<Task>>())
    val taskState = _taskState.asStateFlow()

    private val _taskByIdState = MutableStateFlow(UiState<Task>())
    val taskByIdState = _taskByIdState.asStateFlow()

    init {
        getAllTasks()
    }

    fun updateTask(task: Task) = viewModelScope.launch {
        try {
            repository.update(task)
        }catch (e:Exception){
            Log.d("Update task", "Update task error: $e")
            _taskState.update {
                it.copy(isLoading = false, error = e.message)
            }
            return@launch
        }
    }

    fun deleteTask(task: Task)= viewModelScope.launch {
        try {
            repository.delete(task)
        }catch (e:Exception){
            Log.d("Delete task", "Delete task error: $e")
            _taskState.update {
                it.copy(isLoading = false, error = e.message)
            }
            return@launch
        }
    }

    fun getTaskById(taskId: Long) = viewModelScope.launch {
        _taskByIdState.update { it.copy(isLoading = true) }

        try {
            repository.getTaskById(taskId).collect{ task ->
                _taskByIdState.update {
                    it.copy(isLoading = false, data = task)
                }
            }
        } catch (e: Exception) {
            Log.d("Fetch task by id", "Fetch task by id error: $e")
            _taskByIdState.update {
                it.copy(isLoading = false, error = e.message)
            }
        }
    }

    private fun getAllTasks() = viewModelScope.launch {
        try {
            _taskState.update {it.copy(isLoading = true)}
            //
            repository.getTasksExcludingHighPriority().collect{ tasks ->

                val result = tasks.map { taskWithSubtasks ->
                    val subtasks = taskWithSubtasks.subtasks

                    taskWithSubtasks.task.copy(
                        percentageCompleted = TaskHelper.percentageCompletedPerTask(subtasks),
                        isCompleted = subtasks.isNotEmpty() && subtasks.all { it.isCompleted }
                    )
                }

                _taskState.update {
                    it.copy(isLoading = false, data = result)
                }
            }
        }catch (e: Exception){
            val tag = "Error while getting all tasks"
            Log.d(tag, "$tag - ${e.message}")
            _taskState.update {
                it.copy(isLoading = false, error = e.message)
            }
            return@launch
        }
    }


    fun saveTask(task: Task) = viewModelScope.launch {
        try {
            _taskState.update {it.copy(isLoading = true)}
            //
            repository.insert(task)
            //
            _taskState.update {it.copy(isLoading = false)}
        }catch (e: Exception){
            val tag = "Error while saving task"
            Log.d(tag, "$tag - ${e.message}")
            _taskState.update {
                it.copy(isLoading = false, error = e.message)
            }
            return@launch
        }
    }
}
