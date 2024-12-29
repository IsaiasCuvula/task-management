package com.bersyte.taskmanagement.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun EditTaskButton(onClick: ()-> Unit) {

    IconButton(onClick = onClick) {
        Icon(
            Icons.Outlined.Edit,
            contentDescription = "Edit Task",
            modifier = Modifier.
            background(
                color = colorScheme.surface.copy(alpha = 0.3f),
                shape = CircleShape
            ).padding(6.dp),
            tint = Color.White
        )
    }
}
