package com.bersyte.taskmanagment

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.WindowCompat
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
        // Set light or dark icons
        setStatusBarIconsDark(false)
    }

    private fun setStatusBarIconsDark(isDark: Boolean) {
        val window: Window = window
        val controller = WindowCompat.getInsetsController(window, window.decorView)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            controller.isAppearanceLightStatusBars = isDark
        } else {
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility = if (isDark) {
                window.decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                window.decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
            }
        }
    }
}
