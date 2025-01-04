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
class ScheduleViewModel @Inject constructor(
    private val repository: TaskRepository
): ViewModel() {

    private val _scheduleState = MutableStateFlow(UiState<List<Task>>())
    val scheduleState = _scheduleState.asStateFlow()

    init {
      getAllTasks()
    }

    private fun getAllTasks() = viewModelScope.launch {
        try {
            _scheduleState.update {it.copy(isLoading = true)}
            //
            repository.getAllTasksWithSubtasks().collect{ tasks ->

                val result = tasks.map { taskWithSubtasks ->
                    val subtasks = taskWithSubtasks.subtasks

                    taskWithSubtasks.task.copy(
                        percentageCompleted = TaskHelper.percentageCompletedPerTask(subtasks),
                        isCompleted = subtasks.isNotEmpty() && subtasks.all { it.isCompleted }
                    )
                }

                _scheduleState.update {
                    it.copy(isLoading = false, data = result)
                }
            }
        }catch (e: Exception){
            val tag = "ScheduleViewModel "
            Log.d(tag, "Error while getting all tasks with subtasks - ${e.message}")
            _scheduleState.update {
                it.copy(isLoading = false, error = e.message)
            }
            return@launch
        }
    }

}
