package com.bersyte.taskmanagement.feature.tasks.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bersyte.taskmanagement.utils.AppHelper
import kotlinx.datetime.LocalDateTime

@Entity(tableName = "task_links")
data class TaskLink(
    @PrimaryKey(autoGenerate = true)
    val taskLinkId: Long,
    var url: String,
    val taskOwnerId: Long,
    var createdAt: LocalDateTime = AppHelper.getCurrentDate()
)
