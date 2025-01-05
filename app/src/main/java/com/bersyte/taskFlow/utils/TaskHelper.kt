package com.bersyte.taskFlow.utils

import com.bersyte.taskFlow.feature.tasks.data.models.Subtask
import com.bersyte.taskFlow.feature.tasks.data.models.Task
import kotlinx.datetime.LocalDate
import kotlinx.datetime.minus

object TaskHelper {

    fun percentageCompletedPerTask(subtasks: List<Subtask>): Float {
       val totalSubtasks = subtasks.size
       val completedSubtasks = subtasks.count { it.isCompleted }

       return if (totalSubtasks > 0) {
            (completedSubtasks.toFloat() / totalSubtasks.toFloat())
       } else {
           0f
       }
    }

    fun tasksCompletedPercentage(tasks: List<Task>): Float {
        val total = tasks.size
        val totalDone = totalTasksDone(tasks)
        return if (total == 0) 0f else (totalDone.toFloat() / total)
    }

    fun totalTasksDone(tasks: List<Task>): Int {
        return tasks.count {it.isCompleted}
    }

    fun isTaskDeadline(task: Task): Boolean {
        val taskTime = task.dueTime
        val currentHour = TaskHour.currentHour()
        return currentHour.hour == taskTime.hour
    }

    fun calculateDaysLeft(givenDate: LocalDate): Int {
        val today = AppHelper.getCurrentDate().date
        val result = givenDate.minus(today)
        return if(result.days < 0) 0 else result.days
    }

}
