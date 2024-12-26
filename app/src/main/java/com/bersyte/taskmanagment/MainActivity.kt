package com.bersyte.taskmanagment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.compose.rememberNavController
import com.bersyte.taskmanagment.common.navigation.NavigationGraph
import com.bersyte.taskmanagment.common.theme.TaskManagementTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(
                darkScrim = Color.White.toArgb(),
                lightScrim = Color.White.toArgb()
            ),

        )
        setContent {
            val navController = rememberNavController()
            TaskManagementTheme {
                NavigationGraph(navController)
            }
        }
    }
}
