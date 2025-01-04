package com.bersyte.taskmanagement.feature.home.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bersyte.taskmanagement.common.components.HorizontalSpace
import com.bersyte.taskmanagement.common.components.ThemedCard
import com.bersyte.taskmanagement.common.components.VerticalSpace
import com.bersyte.taskmanagement.common.navigation.Route
import com.bersyte.taskmanagement.feature.tasks.data.models.Task
import com.bersyte.taskmanagement.feature.tasks.ui.components.TaskProgressBar

@Composable
fun HomeThreeTasksCards(
    navController: NavHostController,
    tasks: List<Task>
) {

    val colorScheme = MaterialTheme.colorScheme
    val textStyle = MaterialTheme.typography
    val firstTask = tasks.first()
    val secondTask = tasks[1]
    val thirdTask = tasks[2]

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ThemedCard(
            onClick = {
                navController.navigate(
                    "${Route.TaskDetails.name}/${firstTask.taskId}"
                )
            },
            modifier = Modifier.weight(1f)
                .height(300.dp),
            bgColor = colorScheme.primary,
            content = {
                Column {
                    HomeTaskCardTop(
                        title =  firstTask.title,
                        desc = firstTask.description,
                        taskId = firstTask.taskId,
                        navController = navController,
                    )
                    VerticalSpace(48)
                    TaskProgressBar(percentage = firstTask.percentageCompleted)
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        Alignment.BottomStart
                    ) {
                        Text(
                            "Due: ${firstTask.dueDate}",
                            style = textStyle.labelMedium.copy(
                                color = Color.White.copy(alpha = 0.6f)
                            )
                        )
                    }
                }
            }
        )
        HorizontalSpace(10)
        Column(
            modifier = Modifier.weight(1f)
                .height(300.dp),
        ) {
            ThemedCard(
                onClick = {
                    navController.navigate(
                        "${Route.TaskDetails.name}/${secondTask.taskId}"
                    )
                },
                modifier = Modifier.weight(1f),
                bgColor = colorScheme.secondary,
                content = {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        HomeTaskCardTop(
                            title =  secondTask.title,
                            taskId = secondTask.taskId,
                            navController = navController,
                        )
                        VerticalSpace(24)
                        TaskProgressBar(
                            percentage = secondTask.percentageCompleted,
                            color = colorScheme.onTertiary
                        )
                    }
                }
            )
            VerticalSpace(10)
            ThemedCard(
                onClick = {
                    navController.navigate(
                        "${Route.TaskDetails.name}/${thirdTask.taskId}"
                    )
                },
                bgColor = colorScheme.tertiary,
                content = {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        HomeTaskCardTop(
                            title = thirdTask.title,
                            desc = thirdTask.description,
                            taskId = thirdTask.taskId,
                            descMaxLines = 1,
                            navController = navController,
                        )
                    }
                }
            )
        }
    }
}
