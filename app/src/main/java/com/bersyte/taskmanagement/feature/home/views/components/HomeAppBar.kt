package com.bersyte.taskmanagement.feature.home.views.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Category
import androidx.compose.material.icons.rounded.NotificationsNone
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bersyte.taskmanagement.common.navigation.Route

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeAppBar(navController: NavHostController) {

    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        ),
        navigationIcon = {
            IconButton(
                onClick = {
                    navController.navigate(Route.Tasks.name)
                }
            ) {
                Icon(
                    Icons.Rounded.Category,
                    contentDescription = "",
                    modifier = Modifier.size(32.dp)
                )
            }
        },
        title = {},
        actions = {
            IconButton(
                onClick = {
                    navController.navigate(Route.Notifications.name)
                }
            ) {
                Icon(
                    Icons.Rounded.NotificationsNone,
                    contentDescription = "Open notification page",
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    )
}
