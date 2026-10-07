package com.bersyte.taskflow

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import com.bersyte.taskflow.feature.notifications.service.TaskFlowNotificationService
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TaskFlowApp: Application() {

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    private fun createNotificationChannel(){
        val channel = NotificationChannel(
            TaskFlowNotificationService.TASK_FLOW_CHANNEL_ID,
            "TaskFlow",
            NotificationManager.IMPORTANCE_HIGH
        )
        channel.description = "Used to display task and subtasks"

        val notificationManager = getSystemService(
            NOTIFICATION_SERVICE
        ) as NotificationManager

        notificationManager.createNotificationChannel(channel)
    }
}
