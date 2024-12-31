package com.bersyte.taskmanagement.common.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bersyte.taskmanagement.common.components.WebViewScreen
import com.bersyte.taskmanagement.feature.about.ui.screens.AboutScreen
import com.bersyte.taskmanagement.feature.home.ui.screens.HomeScreen
import com.bersyte.taskmanagement.feature.tasks.ui.screens.TasksScreen
import com.bersyte.taskmanagement.feature.notifications.ui.screens.NotificationScreen
import com.bersyte.taskmanagement.feature.tasks.ui.screens.AddTaskScreen
import com.bersyte.taskmanagement.feature.tasks.ui.screens.EditTaskScreen
import com.bersyte.taskmanagement.feature.tasks.ui.screens.TaskDetailsScreen

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
        composable(Route.TaskDetails.name) { TaskDetailsScreen(navController) }
        composable(Route.Notifications.name) { NotificationScreen(navController) }
        composable(Route.About.name) { AboutScreen(navController) }
        composable(Route.EditTask.name) { EditTaskScreen(navController) }
        composable(Route.AddTask.name) { AddTaskScreen(navController) }
        composable("${Route.WebView.name}/{url}") { backStackEntry ->
            val url = backStackEntry.arguments?.getString("url")
            if(url !=null){
                WebViewScreen(url, navController)
            }
        }
    }

}
