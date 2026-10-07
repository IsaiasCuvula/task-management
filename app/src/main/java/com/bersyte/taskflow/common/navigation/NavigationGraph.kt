package com.bersyte.taskflow.common.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bersyte.taskflow.common.components.WebViewScreen
import com.bersyte.taskflow.feature.about.ui.screens.AboutScreen
import com.bersyte.taskflow.feature.home.ui.screens.HomeScreen
import com.bersyte.taskflow.feature.tasks.ui.screens.TasksScreen
import com.bersyte.taskflow.feature.notifications.ui.screens.NotificationScreen
import com.bersyte.taskflow.feature.tasks.ui.screens.AddTaskScreen
import com.bersyte.taskflow.feature.tasks.ui.screens.EditTaskScreen
import com.bersyte.taskflow.feature.tasks.ui.screens.ScheduleScreen
import com.bersyte.taskflow.feature.tasks.ui.screens.TaskDetailsScreen

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun NavigationGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    NavHost(
        navController= navController,
        startDestination = Route.Home.name,
        modifier= modifier
    ){
        composable(Route.Home.name) { HomeScreen(navController) }
        composable(Route.Tasks.name) { TasksScreen(navController) }
        composable(Route.Notifications.name) { NotificationScreen(navController) }
        composable(Route.About.name) { AboutScreen(navController) }
        composable(Route.AddTask.name) { AddTaskScreen(navController) }
        composable(Route.Schedule.name) { ScheduleScreen(navController) }


        composable("${Route.EditTask.name}/{taskId}") { backStackEntry ->
            val taskId = backStackEntry.arguments?.getString("taskId")
            if(taskId !=null){
                EditTaskScreen(
                    navController = navController,
                    taskId = taskId.toLong()
                )
            }
        }

        composable("${Route.TaskDetails.name}/{taskId}") { backStackEntry ->
            val taskId = backStackEntry.arguments?.getString("taskId")
            if(taskId !=null){
                TaskDetailsScreen(
                    navController = navController,
                    taskId = taskId.toLong()
                )
            }
        }


        composable("${Route.WebView.name}/{url}") { backStackEntry ->
            val url = backStackEntry.arguments?.getString("url")
            if(url !=null){
                WebViewScreen(url, navController)
            }
        }
    }

}
