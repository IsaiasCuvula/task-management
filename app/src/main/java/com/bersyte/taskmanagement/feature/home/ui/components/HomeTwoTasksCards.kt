package com.bersyte.taskmanagement.feature.home.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.bersyte.taskmanagement.common.components.HorizontalSpace
import com.bersyte.taskmanagement.common.components.ProgressBar
import com.bersyte.taskmanagement.common.components.ThemedCard
import com.bersyte.taskmanagement.common.components.VerticalSpace
import com.bersyte.taskmanagement.common.navigation.Route
import com.bersyte.taskmanagement.feature.tasks.data.models.Task

@Composable
fun HomeTwoTasksCards(
    navController: NavHostController,
    tasks: List<Task>
) {
    val colorScheme = MaterialTheme.colorScheme
    val firstTask = tasks.first()
    val secondTask = tasks.last()

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
           modifier = Modifier.weight(1f),
           bgColor = colorScheme.secondary,
           content = {
               Column(
                   modifier = Modifier.fillMaxWidth()
               ) {
                   HomeTaskCardTop(
                       title =  firstTask.title,
                       taskId = firstTask.taskId,
                       navController = navController,
                   )
                   VerticalSpace(24)
                   ProgressBar(percentage = 0.4f,
                       color = colorScheme.onTertiary
                   )
               }
           }
       )
       HorizontalSpace(10)
       ThemedCard(
           onClick = {
               navController.navigate(
                   "${Route.TaskDetails.name}/${secondTask.taskId}"
               )
           },
           modifier = Modifier.weight(1f),
           bgColor = colorScheme.tertiary,
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
                   ProgressBar(percentage = 0.4f,
                       color = colorScheme.secondary
                   )
               }
           }
       )
   }
}
