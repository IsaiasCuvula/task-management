package com.bersyte.taskmanagement.feature.tasks.views.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.navigation.NavHostController
import com.bersyte.taskmanagement.common.components.EditTaskButton
import com.bersyte.taskmanagement.common.components.HorizontalSpace
import com.bersyte.taskmanagement.common.components.VerticalSpace

@Composable
fun DetailsHeader(navController: NavHostController) {

    val colors = MaterialTheme.colorScheme
    val textStyle = MaterialTheme.typography

    Column(
        modifier = Modifier.fillMaxWidth()
            .background(
                color = colors.primary,
                shape = RoundedCornerShape(
                    bottomEnd = 16.dp, bottomStart = 16.dp
                )
            )
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Task title")
            EditTaskButton(navController)
        }
        VerticalSpace(4)
        Text(
            "Landing page design",
            style = textStyle.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                color = Color.White,
            ),
            maxLines = 1
        )
        VerticalSpace(16)
        Text("Due date")
        VerticalSpace(6)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row {
                Icon(
                    Icons.Rounded.AccessTime,
                    contentDescription = "Clock",
                )
                HorizontalSpace(8)
                Text(
                    "2 pm - 3 pm",
                    style = textStyle.bodyLarge
                )
            }
            Row {
                Icon(
                    Icons.Rounded.CalendarMonth,
                    contentDescription = "Calendar",
                )
                HorizontalSpace(8)
                Text(
                    "29 May 2024",
                    style = textStyle.bodyLarge
                )
            }
        }
        VerticalSpace(16)
    }
}
