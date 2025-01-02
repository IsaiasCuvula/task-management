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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bersyte.taskmanagement.common.components.CircularProgressBar
import com.bersyte.taskmanagement.common.components.ThemedCard
import com.bersyte.taskmanagement.common.components.VerticalSpace
import com.bersyte.taskmanagement.common.navigation.Route
import androidx.navigation.NavHostController
import com.bersyte.taskmanagement.feature.tasks.data.models.Task
import com.bersyte.taskmanagement.utils.AppHelper
import com.bersyte.taskmanagement.utils.Month

@Composable
fun TasksCompleted(
    navController: NavHostController,
    tasks: List<Task>
) {
    val textStyle = MaterialTheme.typography
    val today = AppHelper.getCurrentDate().date

    val tasksDoneDisplayText = if(tasks.size != 1)"tasks done" else "task done"

    ThemedCard(
        onClick = {
            navController.navigate(Route.Schedule.name)
        },
        modifier = Modifier.fillMaxWidth(),
        content = {
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
                        "30/${tasks.size} $tasksDoneDisplayText",
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
                CircularProgressBar(percentage = 0.8f)
            }
        }
    )
}
