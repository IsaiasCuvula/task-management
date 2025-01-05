package com.bersyte.taskFlow.feature.tasks.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bersyte.taskFlow.utils.AppHelper
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime


@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val taskId: Long,
    val title: String,
    val isCompleted: Boolean = false,
    val percentageCompleted : Float = 0f,
    val description: String,
    val dueDate: LocalDate,
    val dueTime: LocalTime,
    val priority: TaskPriority,
    val createdAt: LocalDateTime = AppHelper.getCurrentDate()
)
