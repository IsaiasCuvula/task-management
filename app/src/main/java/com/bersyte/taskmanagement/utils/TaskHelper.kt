package com.bersyte.taskmanagement.utils

import com.bersyte.taskmanagement.feature.tasks.data.models.Subtask
import com.bersyte.taskmanagement.feature.tasks.data.models.Task
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.atTime
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
        val taskDate = task.dueDate
        val taskTime = task.dueTime
        val todayDateTime = AppHelper.getCurrentDate()
        val taskDateTime = taskDate.date.atTime(taskTime)
        return todayDateTime == taskDateTime
    }

    fun calculateDaysLeft(givenDateTime: LocalDateTime): Int {
        val today = AppHelper.getCurrentDate().date
        val result = givenDateTime.date.minus(today)
        return if(result.days < 0) 0 else result.days
    }

}
