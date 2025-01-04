package com.bersyte.taskmanagement.feature.tasks.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.bersyte.taskmanagement.common.components.BackButton
import com.bersyte.taskmanagement.common.components.HorizontalSpace
import com.bersyte.taskmanagement.common.components.LoadingIndicator
import com.bersyte.taskmanagement.common.components.ShowErrorMessage
import com.bersyte.taskmanagement.common.components.VerticalSpace
import com.bersyte.taskmanagement.feature.home.ui.components.CalendarWeekView
import com.bersyte.taskmanagement.feature.tasks.ui.components.TaskCard
import com.bersyte.taskmanagement.feature.tasks.viewmodels.ScheduleViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleScreen(
    navController: NavHostController,
    scheduleViewModel: ScheduleViewModel = hiltViewModel()
) {

    val scheduleState = scheduleViewModel.scheduleState.collectAsState()
    val scheduleStateValue = scheduleState.value

    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                ),
                navigationIcon = {
                    BackButton(navController)
                },
                title = {
                    Text("Schedule")
                }
            )
        }
    ){ innerPadding ->

        Column(
            modifier =  Modifier.padding(innerPadding)
        ) {
            CalendarWeekView(
                onDateSelected = {selectedDate ->
                    scheduleViewModel.getAllTasks(selectedDate)
                }
            )
            VerticalSpace(32)
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

                        when{
                            scheduleStateValue.isLoading -> {
                                LoadingIndicator()
                            }

                            scheduleStateValue.error != null -> {
                                ShowErrorMessage(scheduleStateValue.error)
                            }

                            scheduleStateValue.data != null -> {
                                val tasks = scheduleStateValue.data

                                tasks.forEachIndexed { index, task ->

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
                                        TaskCard(navController, task = task)
                                    }
                                    if(index != tasks.lastIndex){
                                        VerticalSpace(8)
                                        HorizontalDivider()
                                        VerticalSpace(8)
                                    }
                                }
                            }
                        }
                        VerticalSpace(24)
                    }
                }
            }
        }
    }
}
