package com.bersyte.taskmanagement.common.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bersyte.taskmanagement.feature.about.views.screens.AboutScreen
import com.bersyte.taskmanagement.feature.home.views.screens.HomeScreen
import com.bersyte.taskmanagement.feature.home.views.screens.TasksScreen
import com.bersyte.taskmanagement.feature.notifications.views.screens.NotificationScreen
import com.bersyte.taskmanagement.feature.tasks.views.screens.TaskDetailsScreen

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
    }

}
