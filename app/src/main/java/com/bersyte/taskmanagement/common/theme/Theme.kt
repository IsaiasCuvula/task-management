package com.bersyte.taskmanagement.common.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


private val DarkColorScheme = darkColorScheme(
    primary = BgColor,
    secondary = Purple,
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
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography,
        content = content
    )
}
