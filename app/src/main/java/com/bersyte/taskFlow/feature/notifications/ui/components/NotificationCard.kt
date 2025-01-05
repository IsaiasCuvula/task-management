package com.bersyte.taskFlow.feature.notifications.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.bersyte.taskFlow.common.components.HorizontalSpace
import com.bersyte.taskFlow.common.components.ThemedCard
import com.bersyte.taskFlow.common.theme.LightGreen
import com.bersyte.taskFlow.feature.notifications.data.models.AppNotification
import com.bersyte.taskmanagment.R

@Composable
fun NotificationCard(
    notification: AppNotification,
    onClick:()-> Unit
) {

    val textStyle = MaterialTheme.typography

    val date = notification.createdAt.date
    val time = notification.createdAt.time
    val notificationMin = if(time.minute < 10) "0${time.minute}" else time.minute
    val isSeen = notification.isSeen

    val titleAlpha = if (isSeen) 0.4f else 1f
    val descAlpha = if (isSeen) 0.2f else 0.4f
    val iconAlpha = if (isSeen) 0.2f else 1f


    ThemedCard(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick,
        content = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painterResource(
                        R.drawable.baseline_task_alt_24
                    ),contentDescription = "",
                    tint = LightGreen.copy(alpha = iconAlpha)
                )
                HorizontalSpace(10)
                Column(
                    modifier = Modifier.weight(1f),
                ) {
                    Text(
                        notification.title,
                        color = Color.White.copy(alpha = titleAlpha)
                    )
                    Text(
                        "Task completed on $date",
                        style = textStyle.labelLarge.copy(
                            color = Color.White.copy(alpha = descAlpha)
                        )
                    )
                }
                Text(
                    "${time.hour}:$notificationMin",
                    color = Color.White.copy(alpha = titleAlpha)
                )
            }
        }
    )

}
