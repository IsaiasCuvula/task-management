package com.bersyte.taskmanagement.common.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat


private val DarkColorScheme = darkColorScheme(
    primary = Purple,
    secondary = LightBlue,
    tertiary = Green,
    background = BgColor,
    surface = CardColor,
    onPrimary = Color.White,
    onSecondary = Blue,
    onTertiary = LightGreen,
    onBackground = Color.White, // Text color on background
    onSurface = Color.White     // Text color on surface
)


@Composable
fun TaskManagementTheme(
    darkMode: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val view = LocalView.current

    if(!view.isInEditMode){
        SideEffect {
            val window = (view.context as Activity).window
            // Set the status bar color to white
            window.statusBarColor = DarkColorScheme.background.toArgb()
            // Set the status bar appearance to "light"
            WindowCompat.getInsetsController(window, view)
                .isAppearanceLightStatusBars = darkMode
        }
    }


    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography,
        content = content
    )
}
