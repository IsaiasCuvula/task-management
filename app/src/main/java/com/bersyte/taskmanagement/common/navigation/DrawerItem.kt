package com.bersyte.taskmanagement.common.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Task
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Task
import androidx.compose.ui.graphics.vector.ImageVector

data class DrawerItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon:ImageVector,
    val route: Route
){
    companion object{
        val drawerItems  = listOf(
            DrawerItem(
                title =  Route.Home.name,
                selectedIcon = Icons.Rounded.Home,
                unselectedIcon =  Icons.Outlined.Home,
                route = Route.Home
            ),
            DrawerItem(
                title =  Route.Tasks.name,
                selectedIcon = Icons.Rounded.Task,
                unselectedIcon =  Icons.Outlined.Task,
                route = Route.Tasks
            ),
            DrawerItem(
                title =  Route.About.name,
                selectedIcon = Icons.Rounded.Info,
                unselectedIcon =  Icons.Outlined.Info,
                route = Route.About
            )
        )
    }
}
