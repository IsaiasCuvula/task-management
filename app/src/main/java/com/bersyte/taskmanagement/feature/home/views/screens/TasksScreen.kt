package com.bersyte.taskmanagement.feature.home.views.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.NotificationsNone
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bersyte.taskmanagement.common.components.BackButton
import com.bersyte.taskmanagement.common.components.HorizontalSpace
import com.bersyte.taskmanagement.common.components.TaskCard
import com.bersyte.taskmanagement.common.components.VerticalSpace
import com.bersyte.taskmanagement.common.navigation.Route

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TasksScreen(navController: NavHostController) {

    val tasks = remember {
        mutableStateListOf("Website frontend", "Website backend", "London")
    }

    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                navigationIcon = {
                    BackButton(navController)
                },
                title = {
                    Text("Schedule")
                },
                actions = {
                    IconButton(
                        onClick = {
                            navController.navigate(Route.Notifications.name)
                        }
                    ) {
                        Icon(
                            Icons.Rounded.NotificationsNone,
                            contentDescription = "Open notification page",
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }
            )
        }
    ){ innerPadding ->

        Column(
            modifier =  Modifier.padding(innerPadding)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
                    .background(color = MaterialTheme.colorScheme.surface)
                    .padding(16.dp)
            ) {
                Text("24")
            }
            VerticalSpace(12)
            LazyColumn {
                item {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(
                            "Today's tasks",
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.Bold
                            )
                        )
                        VerticalSpace(16)
                        tasks.forEachIndexed { index, task ->
                           val taskDeadline = index == 0

                           Row(
                               modifier = Modifier.fillMaxWidth(),
                               horizontalArrangement = Arrangement.Center,
                               verticalAlignment = Alignment.CenterVertically
                           ) {
                               Text(
                                   "10 PM",
                                   style = MaterialTheme.typography.titleMedium.copy(
                                       color = Color.White.copy(alpha = 0.6f)
                                   )
                               )
                               HorizontalSpace(24)
                               TaskCard(isTime = taskDeadline)
                           }
                            if(index != tasks.lastIndex){
                                VerticalSpace(8)
                                HorizontalDivider()
                                Log.d("task", "$task >")
                                VerticalSpace(8)
                            }
                        }
                        VerticalSpace(24)
                    }
                }
            }


        }
    }
}
