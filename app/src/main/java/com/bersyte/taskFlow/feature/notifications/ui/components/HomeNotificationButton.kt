package com.bersyte.taskFlow.feature.notifications.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.NotificationsNone
import androidx.compose.material3.Badge
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.bersyte.taskFlow.common.navigation.Route
import com.bersyte.taskFlow.feature.notifications.viewmodels.NotificationViewModel

@Composable
fun HomeNotificationButton(
    navController: NavHostController,
    notificationViewModel: NotificationViewModel = hiltViewModel()
) {

    val hasNotSeenNotificationsState = notificationViewModel
        .hasNotSeenNotificationsState.collectAsState()
    val hasNotSeenNotificationsStateValue = hasNotSeenNotificationsState.value

    when {
        hasNotSeenNotificationsStateValue.data != null -> {
            val hasNotSeenNotification = hasNotSeenNotificationsStateValue.data

            Box(modifier = Modifier.size(32.dp)) {
                IconButton(
                    onClick = {
                        navController.navigate(Route.Notifications.name)
                    }
                ) {
                    Icon(
                        Icons.Rounded.NotificationsNone,
                        contentDescription = "Notification",
                        modifier = Modifier.size(32.dp)
                    )
                }
                if (hasNotSeenNotification) {
                    Badge(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .size(8.dp),
                        containerColor = Color.Red
                    )
                }
            }
        }
    }
}
