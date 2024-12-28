package com.bersyte.taskmanagement.common.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccessTime
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


@Composable
fun TaskCard(
    isTime: Boolean = false
){
    val textStyle = MaterialTheme.typography
    val colorScheme = MaterialTheme. colorScheme

    val bgColor = if(isTime) colorScheme.primary else colorScheme.surface


    ThemedCard(
        modifier = Modifier.fillMaxWidth(),
        bgColor = bgColor,
        content = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        "Landing page design",
                        style = textStyle.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                        ),
                        maxLines = 1
                    )
                    VerticalSpace(8)
                    Row {
                        Icon(
                            Icons.Rounded.AccessTime,
                            contentDescription = "",
                            tint = Color.White.copy(alpha = 0.6f),
                            modifier = Modifier.size(16.dp)
                        )
                        HorizontalSpace(8)
                        Text(
                            "2 pm - 3 pm",
                            style = textStyle.labelLarge.copy(
                                color = Color.White.copy(alpha = 0.6f)
                            )
                        )
                    }
                    VerticalSpace(4)
                    Row {
                        Icon(
                            Icons.Rounded.CalendarMonth,
                            contentDescription = "",
                            tint = Color.White.copy(alpha = 0.6f),
                            modifier = Modifier.size(16.dp)
                        )
                        HorizontalSpace(8)
                        Text(
                            "June 5",
                            style = textStyle.labelLarge.copy(
                                color = Color.White.copy(alpha = 0.6f)
                            )
                        )
                    }
                }
                CircularProgressBar(
                    percentage = 0.3f, radius = 30,
                    color = if(isTime)colorScheme.onSurface else colorScheme.onSecondary,
                )
            }
        }
    )
}
