package com.bersyte.taskmanagement.feature.home.views.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.bersyte.taskmanagement.common.components.ProgressBar
import com.bersyte.taskmanagement.common.components.ThemedCard
import com.bersyte.taskmanagement.common.components.VerticalSpace

@Composable
fun HomeOneTaskCard() {

    ThemedCard(
        bgColor = colorScheme.primary,
        content = {
            Column {
                HomeTaskCardTop(
                    title =  "UX Design",
                    desc = "Internet banking mobile app",
                    onClick = {}
                )
                VerticalSpace(32)
                ProgressBar(percentage = 0.7f)
                VerticalSpace(16)
                Box(
                    modifier = Modifier.fillMaxSize(),
                    Alignment.BottomStart
                ) {
                    Text(
                        "Due: 31 Dec 2024",
                        style = MaterialTheme.typography.labelMedium.copy(
                            color = Color.White.copy(alpha = 0.6f)
                        )
                    )
                }
            }
        }
    )
}
