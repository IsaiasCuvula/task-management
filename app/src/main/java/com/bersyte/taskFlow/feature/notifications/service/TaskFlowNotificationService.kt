package com.bersyte.taskFlow.feature.notifications.service

import android.Manifest
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.bersyte.taskFlow.MainActivity
import com.bersyte.taskmanagment.R

class TaskFlowNotificationService(
    private val context: Context
): INotificationService {
    private val notificationManager = context
        .getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    override fun showNotification(title: String,description: String, taskId: Int){

        val hasPermission = if(Build.VERSION.SDK_INT == Build.VERSION_CODES.TIRAMISU) hasPermission() else true

        if(hasPermission){
            val activityIntent = Intent(context, MainActivity::class.java)
            val activityPendingIntent = PendingIntent.getActivity(
                context, 1,activityIntent,
                PendingIntent.FLAG_IMMUTABLE
            )

            val notification = NotificationCompat
                .Builder(context, TASK_FLOW_CHANNEL_ID)
                .setSmallIcon(R.drawable.baseline_task_alt_24)
                .setContentTitle(title)
                .setContentText(description)
                .setContentIntent(activityPendingIntent)
                .build()
            notificationManager.notify(taskId, notification)
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun hasPermission(): Boolean{
     return  ContextCompat.checkSelfPermission(
            context, Manifest.permission.POST_NOTIFICATIONS
        ) == PackageManager.PERMISSION_GRANTED
    }

    companion object{
        const val TASK_FLOW_CHANNEL_ID = "task_flow_channel"
    }
}
