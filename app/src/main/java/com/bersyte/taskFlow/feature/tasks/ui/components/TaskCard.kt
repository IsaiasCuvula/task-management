package com.bersyte.taskFlow.feature.tasks.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccessTime
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bersyte.taskFlow.common.components.CircularProgressBar
import com.bersyte.taskFlow.common.components.HorizontalSpace
import com.bersyte.taskFlow.common.components.ThemedCard
import com.bersyte.taskFlow.common.components.VerticalSpace
import com.bersyte.taskFlow.common.navigation.Route
import com.bersyte.taskFlow.feature.tasks.data.models.Task
import com.bersyte.taskFlow.utils.TaskHelper


@Composable
fun TaskCard(
    navController: NavHostController,
    task: Task
){
    val textStyle = MaterialTheme.typography
    val colorScheme = MaterialTheme. colorScheme
    val isTaskDeadline = TaskHelper.isTaskDeadline(task)
    val bgColor = if(isTaskDeadline) colorScheme.primary else colorScheme.surface

    val taskMin = if(task.dueTime.minute == 0) "00" else task.dueTime.minute

    ThemedCard(
        onClick = {
            navController.navigate(
                "${Route.TaskDetails.name}/${task.taskId}"
            )
        },
        modifier = Modifier.fillMaxWidth(),
        bgColor = bgColor,
        content = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                        .weight(3.5f)
                ) {
                    Text(
                        task.title,
                        style = textStyle.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    VerticalSpace(8)
                    Row {
                        Icon(
                            Icons.Rounded.AccessTime,
                            contentDescription = "",
                            tint = Color.White.copy(alpha = 0.6f),
                            modifier = Modifier.size(16.dp)
                        )
                        HorizontalSpace(8)
                        Text(
                           "${task.dueTime.hour}:$taskMin",
                            style = textStyle.labelLarge.copy(
                                color = Color.White.copy(alpha = 0.6f)
                            )
                        )
                    }
                    VerticalSpace(4)
                    Row {
                        Icon(
                            Icons.Rounded.CalendarMonth,
                            contentDescription = "",
                            tint = Color.White.copy(alpha = 0.6f),
                            modifier = Modifier.size(16.dp)
                        )
                        HorizontalSpace(8)
                        Text(
                            task.dueDate.toString(),
                            style = textStyle.labelLarge.copy(
                                color = Color.White.copy(alpha = 0.6f)
                            )
                        )
                    }
                }
                HorizontalSpace(8)
                Column(
                    modifier = Modifier.fillMaxWidth()
                        .weight(1f)

                ) {
                    CircularProgressBar(
                        percentage = task.percentageCompleted, radius = 30,
                        color = if(isTaskDeadline)colorScheme.onSurface else task.priority.color,
                    )
                }
            }
        }
    )
}
