package com.bersyte.taskFlow

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.bersyte.taskFlow.feature.notifications.service.TaskFlowNotificationService
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TaskFlowApp: Application() {

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    private fun createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(
                TaskFlowNotificationService.TASK_FLOW_CHANNEL_ID,
                "TaskFlow",
                NotificationManager.IMPORTANCE_HIGH
            )
            channel.description = "Used to display task and subtasks"

            val notificationManager = getSystemService(
                Context.NOTIFICATION_SERVICE
            ) as NotificationManager

            notificationManager.createNotificationChannel(channel)
        }
    }
}
