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

@Composable
fun HomeTwoTasksCards(navController: NavHostController,) {

    val colorScheme = MaterialTheme.colorScheme

   Row(
       modifier = Modifier.fillMaxWidth(),
       horizontalArrangement = Arrangement.SpaceBetween
   ) {
       ThemedCard(
           onClick = {
               navController.navigate(Route.TaskDetails.name)
           },
           modifier = Modifier.weight(1f),
           bgColor = colorScheme.secondary,
           content = {
               Column(
                   modifier = Modifier.fillMaxWidth()
               ) {
                   HomeTaskCardTop(
                       title =  "API payment",
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
           modifier = Modifier.weight(1f),
           bgColor = colorScheme.tertiary,
           content = {
               Column(
                   modifier = Modifier.fillMaxWidth()
               ) {
                   HomeTaskCardTop(
                       title =  "UX Design",
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
