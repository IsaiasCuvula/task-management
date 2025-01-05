package com.bersyte.taskFlow.feature.notifications.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.bersyte.taskFlow.common.components.BackButton
import com.bersyte.taskFlow.common.components.HorizontalSpace
import com.bersyte.taskFlow.common.components.LoadingIndicator
import com.bersyte.taskFlow.common.components.NoTasksMessageCard
import com.bersyte.taskFlow.common.components.ShowErrorMessage
import com.bersyte.taskFlow.common.components.ThemedCard
import com.bersyte.taskFlow.common.components.VerticalSpace
import com.bersyte.taskFlow.common.navigation.Route
import com.bersyte.taskFlow.common.theme.LightGreen
import com.bersyte.taskFlow.feature.notifications.viewmodels.NotificationViewModel
import com.bersyte.taskmanagment.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationScreen(
    navController: NavHostController,
    notificationViewModel: NotificationViewModel = hiltViewModel()
) {

    val notificationsState = notificationViewModel.notificationsState.collectAsState()
    val notificationsStateValue = notificationsState.value

    val textStyle = MaterialTheme.typography
    val colors = MaterialTheme.colorScheme

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Notifications") },
                navigationIcon = {BackButton(navController)},
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                ),
            )
        }
    ) { innerPadding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {

            item {
                when {
                    notificationsStateValue.isLoading -> {
                        LoadingIndicator()
                    }

                    notificationsStateValue.error != null -> {
                        ShowErrorMessage(notificationsStateValue.error)
                    }

                    notificationsStateValue.data != null -> {
                        val notifications = notificationsStateValue.data


                        if(notifications.isEmpty()){
                            NoTasksMessageCard(
                                navController = navController,
                                msg = "Hi, There is no notification yet"
                            )
                        }else{
                            VerticalSpace(16)
                            notifications.forEach{ notification ->
                                val date = notification.createdAt.date
                                val time = notification.createdAt.time
                                val notificationMin = if(time.minute < 10) "0${time.minute}" else time.minute
                                val isSeen = notification.isSeen
                                val bgColor = if(isSeen)colors.surface.copy(
                                    alpha = 0.5f
                                ) else colors.surface

                                val titleColor = if(isSeen) Color.White.copy(alpha = 0.4f) else Color.White
                                val descColor = if(isSeen) Color.White.copy(alpha = 0.2f) else Color.White.copy(alpha = 0.4f)
                                val iconColor = if(isSeen) LightGreen.copy(alpha = 0.2f) else LightGreen

                                ThemedCard(
                                    modifier = Modifier.fillMaxWidth(),
                                    bgColor = bgColor,
                                    onClick = {

                                        if(!isSeen){
                                            val notificationUpdated = notification.copy(
                                                isSeen = true
                                            )
                                            notificationViewModel.updateNotification(
                                                notificationUpdated
                                            )
                                        }

                                        //
                                        navController.navigate(
                                            "${Route.TaskDetails.name}/${notification.taskId}"
                                        )
                                    },
                                    content = {
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Icon(
                                                painterResource(
                                                    R.drawable.baseline_task_alt_24
                                                ),contentDescription = "",
                                                tint = iconColor
                                            )
                                            HorizontalSpace(10)
                                            Column(
                                                modifier = Modifier.weight(1f),
                                            ) {
                                                Text(notification.title, color = titleColor)
                                                Text(
                                                    "Task completed on $date",
                                                    style = textStyle.labelLarge.copy(
                                                        color = descColor
                                                    )
                                                )
                                            }
                                            Text(
                                                "${time.hour}:$notificationMin",
                                                color = titleColor
                                            )
                                        }
                                    }
                                )
                                VerticalSpace(10)
                            }
                        }
                    }

                }
            }
        }
    }
}
