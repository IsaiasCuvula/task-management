package com.bersyte.taskflow.feature.home.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.bersyte.taskflow.common.components.HorizontalSpace
import com.bersyte.taskflow.common.components.ThemedCard
import com.bersyte.taskflow.common.components.VerticalSpace
import com.bersyte.taskflow.common.navigation.Route
import com.bersyte.taskflow.feature.tasks.data.models.Task
import com.bersyte.taskflow.feature.tasks.ui.components.TaskProgressBar

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
                   TaskProgressBar(
                       percentage = firstTask.percentageCompleted,
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
                   TaskProgressBar(
                       percentage = secondTask.percentageCompleted,
                       color = colorScheme.secondary
                   )
               }
           }
       )
   }
}
