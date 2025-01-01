package com.bersyte.taskmanagement.feature.tasks.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bersyte.taskmanagement.common.components.EditTaskButton
import com.bersyte.taskmanagement.common.components.HorizontalSpace
import com.bersyte.taskmanagement.common.components.VerticalSpace
import com.bersyte.taskmanagement.feature.tasks.data.models.Task

@Composable
fun DetailsHeader(navController: NavHostController, task: Task) {

    val colors = MaterialTheme.colorScheme
    val textStyle = MaterialTheme.typography

    Column(
        modifier = Modifier.fillMaxWidth()
            .background(
                color = colors.primary,
                shape = RoundedCornerShape(
                    bottomEnd = 16.dp, bottomStart = 16.dp
                )
            )
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Task title")
            EditTaskButton(navController)
        }
        VerticalSpace(4)
        Text(
            task.title,
            style = textStyle.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                color = Color.White,
            ),
            maxLines = 1
        )
        VerticalSpace(16)

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text("Due date")
                VerticalSpace(6)
                Row {
                    Icon(
                        Icons.Rounded.CalendarMonth,
                        contentDescription = "Calendar",
                    )
                    HorizontalSpace(8)
                    Text(
                        task.dueDate.date.toString(),
                        style = textStyle.bodyLarge
                    )
                }

            }
            Column {
                Text("Due time")
                VerticalSpace(6)
                Row {
                    Icon(
                        Icons.Rounded.AccessTime,
                        contentDescription = "Clock",
                    )
                    HorizontalSpace(8)
                    Text(
                        "${task.dueTime.hour}:${task.dueTime.minute}",
                        style = textStyle.bodyLarge
                    )
                }
            }
        }
        VerticalSpace(16)
    }
}
