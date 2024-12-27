package com.bersyte.taskmanagement.common.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CircularProgressBar(
    percentage: Float,
    number: Int = 100,
    radius: Dp = 40.dp,
    color: Color = MaterialTheme.colorScheme.onSecondary,
    strokeWidth: Dp = 8.dp,
    animationDuration: Int = 1000,
    animationDelay: Int = 0
) {

    var animationPlayed by remember { mutableStateOf(false) }
    val curPercentage = animateFloatAsState(
        targetValue = if(animationPlayed) percentage else 0f,
        animationSpec = tween(
            durationMillis = animationDuration,
            delayMillis = animationDelay
        ), label = ""
    )

    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }

    val colorScheme = MaterialTheme.colorScheme
    val startAngle = -90f
    val sweepAngle = 360f

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size( radius * 2f)
    ){
        Canvas(
            modifier = Modifier.size(radius * 2f)
        ) {
            val strokeStyle = Stroke(
                strokeWidth.toPx(),
                cap = StrokeCap.Round
            )

            drawArc(
                color = colorScheme.background,
                startAngle, sweepAngle,
                useCenter = false,
                style = strokeStyle
            )

            drawArc(
                color = color, startAngle,
                sweepAngle * curPercentage.value,
                useCenter = false,
                style = strokeStyle
            )
        }
        Text(
            "${(curPercentage.value * number).toInt()}%",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold
            )
        )
    }
}
