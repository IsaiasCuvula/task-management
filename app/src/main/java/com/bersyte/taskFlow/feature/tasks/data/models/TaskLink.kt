package com.bersyte.taskFlow.feature.tasks.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bersyte.taskFlow.utils.AppHelper
import kotlinx.datetime.LocalDateTime

@Entity(tableName = "task_links")
data class TaskLink(
    @PrimaryKey(autoGenerate = true)
    val taskLinkId: Long,
    var url: String,
    val taskOwnerId: Long,
    var createdAt: LocalDateTime = AppHelper.getCurrentDate()
)
