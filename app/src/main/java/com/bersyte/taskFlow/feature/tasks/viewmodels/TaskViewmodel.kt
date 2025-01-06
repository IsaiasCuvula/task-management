package com.bersyte.taskFlow.feature.tasks.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bersyte.taskFlow.core.ui.UiState
import com.bersyte.taskFlow.feature.tasks.data.models.Task
import com.bersyte.taskFlow.feature.tasks.data.repositories.task.ITaskRepository
import com.bersyte.taskFlow.utils.TaskHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException


@HiltViewModel
class TaskViewmodel @Inject constructor(
    private val repository: ITaskRepository
): ViewModel() {

    private val _taskState = MutableStateFlow(UiState<List<Task>>())
    val taskState = _taskState.asStateFlow()

    private val _taskByIdState = MutableStateFlow(UiState<Task>())
    val taskByIdState = _taskByIdState.asStateFlow()

    init {
        getTasksExcludingHighPriority()
        getAllTasks()
    }

    private fun getAllTasks() = viewModelScope.launch {
        try {
            _taskState.update {it.copy(isLoading = true)}
            //
            repository.getAllTasksWithSubtasks().collect{ tasks ->

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
        }catch (e: CancellationException){
            throw e
        }catch (e: Exception){
            Log.d("TaskViewmodel", "Getting all tasks with subtasks exception- $e")
            _taskState.update {
                it.copy(isLoading = false, error = e.message)
            }
            return@launch
        }
    }

    fun updateTask(task: Task) = viewModelScope.launch {
        try {
            repository.update(task)
        }catch (e: CancellationException){
            throw e
        }catch (e:Exception){
            Log.d("TaskViewmodel", "Update task error: $e")
            _taskState.update {
                it.copy(isLoading = false, error = e.message)
            }
            return@launch
        }
    }

    fun deleteTask(task: Task)= viewModelScope.launch {
        try {
            repository.delete(task)
        }catch (e: CancellationException){
            throw e
        }catch (e:Exception){
            Log.d("TaskViewmodel", "Delete task error: $e")
            _taskState.update {
                it.copy(isLoading = false, error = e.message)
            }
            return@launch
        }
    }

    fun getTaskById(taskId: Long) = viewModelScope.launch {
        _taskByIdState.update { it.copy(isLoading = true) }

        try {
            repository.getTaskById(taskId).collect{ taskWithSubtasks ->

                if(taskWithSubtasks != null){
                    val subtasks = taskWithSubtasks.subtasks

                  val result =  taskWithSubtasks.task.copy(
                        percentageCompleted = TaskHelper.percentageCompletedPerTask(subtasks),
                        isCompleted = subtasks.isNotEmpty() && subtasks.all { it.isCompleted }
                    )

                  _taskByIdState.update {
                      it.copy(isLoading = false, data = result)
                  }
                }
            }
        } catch (e: CancellationException){
            throw e
        }catch (e: Exception) {
            Log.d("TaskViewmodel", "Fetch task by id error: $e")
            _taskByIdState.update {
                it.copy(isLoading = false, error = e.message)
            }
        }
    }

    private fun getTasksExcludingHighPriority() = viewModelScope.launch {
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
        }catch (e: CancellationException){
            throw e
        }catch (e: Exception){
            Log.d("TaskViewmodel", "Getting all tasks - ${e.message}")
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
        }catch (e: CancellationException){
            throw e
        }catch (e: Exception){
            Log.d("TaskViewmodel", "Saving task exception - $e")
            _taskState.update {
                it.copy(isLoading = false, error = e.message)
            }
            return@launch
        }
    }
}
