package com.bersyte.taskflow.feature.tasks.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bersyte.taskflow.core.ui.UiState
import com.bersyte.taskflow.feature.tasks.data.models.Task
import com.bersyte.taskflow.feature.tasks.data.repositories.task.ITaskRepository
import com.bersyte.taskflow.utils.AppHelper
import com.bersyte.taskflow.utils.TaskHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val repository: ITaskRepository
): ViewModel() {

    private val _scheduleState = MutableStateFlow(UiState<List<Task>>())
    val scheduleState = _scheduleState.asStateFlow()

    init {
      getAllTasks(AppHelper.getCurrentDate().date)
    }

    fun getAllTasks(date: LocalDate) = viewModelScope.launch {
        try {
            _scheduleState.update {it.copy(isLoading = true)}
            //
            repository.getTasksByDueDate(date.toString()).collect{ tasks ->

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
        }catch (e: CancellationException){
            throw e
        }catch (e: Exception){
            Log.d("ScheduleViewModel", "Getting all tasks with subtasks exception- $e")
            _scheduleState.update {
                it.copy(isLoading = false, error = e.message)
            }
            return@launch
        }
    }

}
