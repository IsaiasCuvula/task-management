package com.bersyte.taskmanagement.feature.tasks.viewmodels

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
class TaskViewmodel @Inject constructor(
    private val repository: TaskRepository
): ViewModel() {

    private val _taskState = MutableStateFlow(UiState<List<Task>>())
    val taskState = _taskState.asStateFlow()


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
