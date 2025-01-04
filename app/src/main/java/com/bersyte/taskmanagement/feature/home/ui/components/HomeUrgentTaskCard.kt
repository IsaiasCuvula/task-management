package com.bersyte.taskmanagement.feature.home.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bersyte.taskmanagement.common.components.CircularProgressBar
import com.bersyte.taskmanagement.common.components.HorizontalSpace
import com.bersyte.taskmanagement.common.components.ThemedCard
import com.bersyte.taskmanagement.common.components.VerticalSpace
import com.bersyte.taskmanagement.feature.tasks.data.models.Task
import com.bersyte.taskmanagement.utils.TaskHelper

@Composable
fun HomeUrgentTaskCard(
    onClick: ()-> Unit,
    task: Task
) {
    val textStyle = MaterialTheme.typography
    val daysLeft = TaskHelper.calculateDaysLeft(task.dueDate)
    val daysLeftText = if(daysLeft == 1) "1 day left" else "$daysLeft days left"

    ThemedCard(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        content = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CircularProgressBar(
                    percentage = task.percentageCompleted,
                    radius = 28, strokeWidth = 6.dp,
                    color = task.priority.color
                )
                HorizontalSpace(16)
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        task.title,
                        style = textStyle.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                        ),
                        maxLines = 1
                    )
                    VerticalSpace(8)
                    Text(
                        task.description,
                        style = textStyle.labelLarge.copy(
                            color = Color.White.copy(alpha = 0.6f)
                        ),
                        maxLines = 2
                    )

                }
                HorizontalSpace(16)
                Text(
                    daysLeftText,
                    style = textStyle.labelLarge.copy(
                        color = Color.White.copy(alpha = 0.6f)
                    ),
                    maxLines = 2
                )
            }
        }
    )
}
