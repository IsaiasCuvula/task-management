package com.bersyte.taskflow.feature.notifications.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.bersyte.taskflow.common.components.BackButton
import com.bersyte.taskflow.common.components.LoadingIndicator
import com.bersyte.taskflow.common.components.NoTasksMessageCard
import com.bersyte.taskflow.common.components.ShowErrorMessage
import com.bersyte.taskflow.common.components.SwipeToDeleteContainer
import com.bersyte.taskflow.common.components.VerticalSpace
import com.bersyte.taskflow.common.navigation.Route
import com.bersyte.taskflow.feature.notifications.ui.components.NotificationCard
import com.bersyte.taskflow.feature.notifications.viewmodels.NotificationViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationScreen(
    navController: NavHostController,
    notificationViewModel: NotificationViewModel = hiltViewModel()
) {

    val notificationsState = notificationViewModel.notificationsState.collectAsState()
    val notificationsStateValue = notificationsState.value



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
                                val isSeen = notification.isSeen


                                key(notification.id) {
                                    SwipeToDeleteContainer(
                                        item = notification,
                                        onDelete = { notificationToDelete ->
                                            notificationViewModel.deleteNotification(
                                                notificationToDelete
                                            )
                                        },
                                        content = {
                                            NotificationCard(
                                                notification = notification,
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
                                                }
                                            )
                                        }
                                    )
                                }
                                VerticalSpace(10)
                            }
                        }
                    }
                }
            }
        }
    }
}
