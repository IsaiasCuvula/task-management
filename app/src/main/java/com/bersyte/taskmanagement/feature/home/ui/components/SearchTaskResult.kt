package com.bersyte.taskmanagement.feature.home.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import com.bersyte.taskmanagement.common.components.ThemedCard
import com.bersyte.taskmanagement.common.components.VerticalSpace
import com.bersyte.taskmanagement.common.navigation.Route
import com.bersyte.taskmanagement.feature.tasks.data.models.Task

@Composable
fun SearchTaskResult(
    query: String,
    allTask: List<Task>,
    navController: NavHostController
) {

    val tasks = allTask.filter { task ->
        task.description.lowercase().contains(query.lowercase()) ||
        task.title.lowercase().contains(query.lowercase()) ||
        task.priority.name.lowercase().contains(query.lowercase())
    }

    if(tasks.isEmpty()){
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            ThemedCard(
                modifier = Modifier.fillMaxWidth(),
                content = {
                    Text(
                        "No task with - $query",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
            )
        }
    }else{
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                "Search tasks results",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            VerticalSpace(16)
            tasks.forEachIndexed { index, task ->
                HomeUrgentTaskCard(
                    task = task,
                    onClick = {
                        navController.navigate(
                            "${Route.TaskDetails.name}/${task.taskId}"
                        )
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
