package com.bersyte.taskmanagement.feature.home.views.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bersyte.taskmanagement.common.components.CircularProgressBar
import com.bersyte.taskmanagement.common.components.HorizontalSpace
import com.bersyte.taskmanagement.common.components.ThemedCard
import com.bersyte.taskmanagement.common.components.VerticalSpace

@Composable
fun HomeUrgentTaskCard() {
    val textStyle = MaterialTheme.typography

    ThemedCard(
        modifier = Modifier.fillMaxWidth(),
        content = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CircularProgressBar(
                    percentage = 0.3f, radius = 28, strokeWidth = 6.dp
                )
                HorizontalSpace(16)
                Column(
                    modifier = Modifier.weight(1f),

                ) {
                    Text(
                        "Landing page design",
                        style = textStyle.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                        ),
                        maxLines = 1
                    )
                    VerticalSpace(8)
                    Text(
                        "Make user and admin dashboard looks really nice and amazing",
                        style = textStyle.labelLarge.copy(
                            color = Color.White.copy(alpha = 0.6f)
                        ),
                        maxLines = 2
                    )

                }
                HorizontalSpace(16)
                Text(
                    "1 day left",
                    style = textStyle.labelLarge.copy(
                        color = Color.White.copy(alpha = 0.6f)
                    ),
                    maxLines = 2
                )

            }
        }
    )
}
