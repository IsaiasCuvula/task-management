package com.bersyte.taskmanagement.common.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ProgressBar(
    percentage: Float,
    height: Int = 8,
    totalPerc: Int = 100,
    color: Color = colorScheme.onSecondary,
    trackColor: Color =  colorScheme.background,
    animationDuration: Int = 1000,
    animationDelay: Int = 0
){

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

    val textStyle = MaterialTheme.typography

    Column {
        LinearProgressIndicator(
            progress = { curPercentage.value },
            modifier = Modifier
                .fillMaxWidth()
                .height(height.dp)
                .clip(shape = RoundedCornerShape(16.dp)),
            trackColor = trackColor.copy(alpha = 0.4f),
            color = color
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                "Progress",
                style = textStyle.labelSmall.copy(
                    color = Color.White.copy(alpha = 0.6f)
                )
            )

            Text(
                "${(curPercentage.value * totalPerc).toInt()}%",
                style = textStyle.labelSmall.copy(
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}
