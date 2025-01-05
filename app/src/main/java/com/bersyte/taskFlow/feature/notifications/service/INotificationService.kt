package com.bersyte.taskFlow.feature.notifications.service

interface INotificationService {
    fun showNotification(title: String, description: String, taskId: Int)
}
