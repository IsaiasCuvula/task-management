package com.bersyte.taskmanagement.feature.home.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bersyte.taskmanagement.core.ui.UiState
import com.bersyte.taskmanagement.feature.tasks.data.models.Task
import com.bersyte.taskmanagement.feature.tasks.data.models.TaskPriority
import com.bersyte.taskmanagement.feature.tasks.data.repositories.task.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val taskRepository: TaskRepository,
): ViewModel() {

    private val _homeState = MutableStateFlow(UiState<List<Task>>())
    val homeState = _homeState.asStateFlow()

    private val _homeUrgentTaskState = MutableStateFlow(UiState<List<Task>>())
    val homeUrgentTaskState = _homeUrgentTaskState.asStateFlow()

    fun getTasks() = viewModelScope.launch {
        try {
            _homeState.update {it.copy(isLoading = true)}
            //
            taskRepository.getAllTasks().collect{ task ->
                _homeState.update {
                    it.copy(isLoading = false, data = task.filter { task ->
                        task.priority != TaskPriority.HIGH
                    })
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

     fun getThreeUrgentTasks() = viewModelScope.launch {
        try {
            _homeUrgentTaskState.update {it.copy(isLoading = true)}
            //
            taskRepository.getTasksByPriority(TaskPriority.HIGH).collect{ task ->
                _homeUrgentTaskState.update {
                    it.copy(isLoading = false, data = task)
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
