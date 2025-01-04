package com.bersyte.taskmanagement.feature.home.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import com.bersyte.taskmanagement.common.components.EditTaskButton
import com.bersyte.taskmanagement.common.components.ThemedCard
import com.bersyte.taskmanagement.common.components.VerticalSpace
import com.bersyte.taskmanagement.common.navigation.Route
import com.bersyte.taskmanagement.feature.tasks.data.models.Task
import com.bersyte.taskmanagement.feature.tasks.ui.components.TaskProgressBar

@Composable
fun HomeOneTaskCard(
    navController: NavHostController,
    tasks: List<Task>
) {
    val textStyle = MaterialTheme.typography
    val firstTask = tasks.first()

    ThemedCard(
        onClick = {
            navController.navigate(
                "${Route.TaskDetails.name}/${firstTask.taskId}"
            )
        },
        bgColor = colorScheme.primary,
        content = {
           Column(
              modifier = Modifier.fillMaxWidth()
           ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth().weight(1f)
                    ) {
                        Text(
                            firstTask.title,
                            style = textStyle.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            ),
                            maxLines = 1
                        )
                        Text(
                            firstTask.description,
                            style = textStyle.bodyMedium.copy(
                                color = Color.White
                            ),
                            maxLines = 1
                        )
                    }
                    EditTaskButton(
                        navController = navController,
                        taskId = firstTask.taskId
                    )
                }
                VerticalSpace(32)
                TaskProgressBar(
                    percentage = firstTask.percentageCompleted,
                    color = colorScheme.tertiary,
                    trackColor = colorScheme.surface
                )
                VerticalSpace(16)
                Box(
                    modifier = Modifier.fillMaxSize(),
                    Alignment.BottomStart
                ) {
                    Text(
                        "Due: ${firstTask.dueDate}",
                        style = MaterialTheme.typography.labelMedium.copy(
                            color = Color.White.copy(alpha = 0.6f)
                        )
                    )
                }
           }
        }
    )
}
