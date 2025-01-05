package com.bersyte.taskFlow.feature.tasks.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bersyte.taskFlow.utils.AppHelper
import kotlinx.datetime.LocalDateTime


@Entity(tableName = "subtasks")
data class Subtask(
    @PrimaryKey(autoGenerate = true)
    val subtaskId: Long,
    var title: String,
    var isCompleted: Boolean = false,
    val taskOwnerId: Long,
    var createdAt: LocalDateTime = AppHelper.getCurrentDate()
)
