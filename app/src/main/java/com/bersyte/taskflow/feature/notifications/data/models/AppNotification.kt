package com.bersyte.taskflow.feature.notifications.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bersyte.taskflow.utils.AppHelper
import kotlinx.datetime.LocalDateTime

@Entity(tableName = "notifications")
data class AppNotification(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val taskId: Long,
    val isSeen: Boolean = false,
    val createdAt: LocalDateTime = AppHelper.getCurrentDate()
)
