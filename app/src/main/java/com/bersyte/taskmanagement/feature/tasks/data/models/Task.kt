package com.bersyte.taskmanagement.feature.tasks.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bersyte.taskmanagement.utils.AppHelper
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime


@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val taskId: Long,
    var title: String,
    var isCompleted: Boolean = false,
    var description: String,
    var dueDate: LocalDateTime,
    var dueTime: LocalTime,
    var priority: TaskPriority,
    var createdAt: LocalDateTime = AppHelper.getCurrentDate()
)
