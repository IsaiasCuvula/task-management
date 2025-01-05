package com.bersyte.taskFlow.feature.notifications.data.repositories

import com.bersyte.taskFlow.core.db.AppDatabase
import com.bersyte.taskFlow.feature.notifications.data.models.AppNotification
import kotlinx.coroutines.flow.Flow

class NotificationRepository(db: AppDatabase): INotificationRepository {

    private val dao = db.notificationDao()

    override suspend fun insert(notification: AppNotification) = dao.insert(notification)
    override suspend fun delete(notification: AppNotification)= dao.delete(notification)
    override suspend fun update(notification: AppNotification) = dao.update(notification)
    override suspend fun notificationByTaskId(taskId: Long): Flow<AppNotification?> {
       return dao.notificationByTaskId(taskId)
    }

    override suspend fun getAllNotifications(): Flow<List<AppNotification>> {
       return dao.getAllNotifications()
    }
}
