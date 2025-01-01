package com.bersyte.taskmanagement.feature.home.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import com.bersyte.taskmanagement.common.components.ThemedCard
import com.bersyte.taskmanagement.common.components.VerticalSpace
import com.bersyte.taskmanagement.common.navigation.Route
import com.bersyte.taskmanagement.feature.tasks.data.models.Task

@Composable
fun UrgentTasks(
    navController: NavHostController,
    tasks: List<Task>
) {

    if(tasks.isEmpty()){
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            ThemedCard(
                modifier = Modifier.fillMaxWidth(),
                content = {
                    Text("No urgent task to-do")
                }
            )
        }
    }else{
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                "Urgent tasks",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            VerticalSpace(16)
            tasks.forEachIndexed { index, task ->
                HomeUrgentTaskCard(
                    task = task,
                    onClick = {
                        navController.navigate(Route.TaskDetails.name)
                    }
                )
                if(index != tasks.lastIndex){
                    VerticalSpace(8)
                    HorizontalDivider()
                    VerticalSpace(8)
                }
            }
            VerticalSpace(32)
        }
    }
}
