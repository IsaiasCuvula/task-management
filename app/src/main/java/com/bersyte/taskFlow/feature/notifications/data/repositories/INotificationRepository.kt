package com.bersyte.taskFlow.feature.notifications.data.repositories

import com.bersyte.taskFlow.feature.notifications.data.models.AppNotification
import kotlinx.coroutines.flow.Flow

interface INotificationRepository {
    suspend fun insert(notification: AppNotification)
    suspend fun delete(notification: AppNotification)
    suspend fun update(notification: AppNotification)
    suspend fun notificationByTaskId(taskId: Long):  Flow<AppNotification?>
    suspend fun getAllNotifications(): Flow<List<AppNotification>>
}
