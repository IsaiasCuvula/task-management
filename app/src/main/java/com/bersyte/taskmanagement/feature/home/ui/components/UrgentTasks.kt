package com.bersyte.taskmanagement.feature.home.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.bersyte.taskmanagement.common.components.LoadingIndicator
import com.bersyte.taskmanagement.common.components.ShowErrorMessage
import com.bersyte.taskmanagement.common.components.ThemedCard
import com.bersyte.taskmanagement.common.components.VerticalSpace
import com.bersyte.taskmanagement.common.navigation.Route
import com.bersyte.taskmanagement.feature.home.viewmodels.HomeViewModel

@Composable
fun UrgentTasks(
    navController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {

    val urgentTaskState = homeViewModel.homeUrgentTaskState.collectAsState()
    val tasksValue = urgentTaskState.value

    when{
        tasksValue.isLoading ->{
            LoadingIndicator()
        }

        tasksValue.error != null -> {
            ShowErrorMessage(tasksValue.error)
        }
        tasksValue.data != null -> {
            val tasks = tasksValue.data

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
    }
}
