package com.bersyte.taskmanagement.feature.home.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bersyte.taskmanagement.common.components.CircularProgressBar
import com.bersyte.taskmanagement.common.components.ThemedCard
import com.bersyte.taskmanagement.common.components.VerticalSpace
import com.bersyte.taskmanagement.common.navigation.Route
import androidx.navigation.NavHostController
import com.bersyte.taskmanagement.feature.home.viewmodels.TasksCompletedViewModel
import com.bersyte.taskmanagement.utils.AppHelper
import com.bersyte.taskmanagement.utils.Month
import com.bersyte.taskmanagement.utils.TaskHelper

@Composable
fun TasksCompleted(
    navController: NavHostController,
    tasksCompletedVM: TasksCompletedViewModel = hiltViewModel()
) {
    val tasksCompletedState = tasksCompletedVM.tasksCompletedState.collectAsState()
    val tasksCompletedStateValue = tasksCompletedState.value

    val textStyle = MaterialTheme.typography
    val today = AppHelper.getCurrentDate().date


    ThemedCard(
        onClick = {
            navController.navigate(Route.Tasks.name)
        },
        modifier = Modifier.fillMaxWidth(),
        content = {
           when{
               tasksCompletedStateValue.data != null -> {
                   val tasks = tasksCompletedStateValue.data
                   val percentageCompleted = TaskHelper.tasksCompletedPercentage(tasks)
                   val totalDone = TaskHelper.totalTasksDone(tasks)
                   val tasksDoneDisplayText = if (tasks.size != 1) "tasks done" else "task done"

                   Row(
                       modifier = Modifier.fillMaxWidth(),
                       horizontalArrangement = Arrangement.SpaceBetween,
                       verticalAlignment = Alignment.CenterVertically
                   ) {
                       Column {
                           Text(
                               "Task Progress",
                               style = textStyle.titleLarge.copy(
                                   fontWeight = FontWeight.Bold
                               )
                           )
                           VerticalSpace(6)
                           Text(
                               "$totalDone/${tasks.size} $tasksDoneDisplayText",
                               style = textStyle.bodyMedium.copy(
                                   color = Color.White.copy(alpha = 0.6f)
                               )
                           )
                           VerticalSpace(10)
                           Text(
                               "${Month.getName(today.monthNumber)} ${today.dayOfMonth}",
                               modifier = Modifier
                                   .background(
                                       color = colorScheme.onSecondary,
                                       shape = RoundedCornerShape(16.dp)
                                   )
                                   .padding(horizontal = 10.dp)
                           )
                       }
                       CircularProgressBar(percentage = percentageCompleted)
                   }
               }
           }
        }
    )
}
