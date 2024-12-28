package com.bersyte.taskmanagement.feature.home.views.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bersyte.taskmanagement.common.components.HorizontalSpace
import com.bersyte.taskmanagement.common.components.ProgressBar
import com.bersyte.taskmanagement.common.components.ThemedCard
import com.bersyte.taskmanagement.common.components.VerticalSpace

@Composable
fun HomeThreeTasksCards() {

    val colorScheme = MaterialTheme.colorScheme
    val textStyle = MaterialTheme.typography

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ThemedCard(
            modifier = Modifier.weight(1f)
                .height(300.dp),
            bgColor = colorScheme.primary,
            content = {
                Column {
                    HomeTaskCardTop(
                        title =  "UX Design",
                        desc = "Internet banking mobile app",
                        onClick = {}
                    )
                    VerticalSpace(48)
                    ProgressBar(percentage = 0.7f)
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        Alignment.BottomStart
                    ) {
                        Text(
                            "Due: 31 Dec 2024",
                            style = textStyle.labelMedium.copy(
                                color = Color.White.copy(alpha = 0.6f)
                            )
                        )
                    }
                }
            }
        )
        HorizontalSpace(10)
        Column(
            modifier = Modifier.weight(1f)
                .height(300.dp),
        ) {
            ThemedCard(
                modifier = Modifier.weight(1f),
                bgColor = colorScheme.secondary,
                content = {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        HomeTaskCardTop(
                            title =  "API payment",
                            onClick = {}
                        )
                        VerticalSpace(24)
                        ProgressBar(percentage = 0.4f,
                            color = colorScheme.onTertiary
                        )
                    }
                }
            )
            VerticalSpace(10)
            ThemedCard(
                bgColor = colorScheme.tertiary,
                content = {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        HomeTaskCardTop(
                            title =  "Update work",
                            desc = "Review home page",
                            descMaxLines = 1,
                            onClick = {}
                        )
                    }
                }
            )
        }
    }
}
