package com.bersyte.taskmanagement.feature.home.views.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun HomeTaskCardTop(
    onClick: ()-> Unit,
    title: String,
    desc: String = "",
) {
    val textStyle = MaterialTheme.typography

    Column {
        IconButton(onClick = onClick) {
            Icon(
                Icons.Outlined.Edit,
                contentDescription = "",
                modifier = Modifier.
                background(
                    color = colorScheme.surface.copy(alpha = 0.3f),
                    shape = CircleShape
                ).padding(6.dp),
                tint = Color.White
            )
        }
        Text(
            title,
            style = textStyle.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                color = Color.White
            ),
            maxLines = 1
        )
        if(desc.isNotEmpty()){
            Text(
                desc,
                style = textStyle.bodyMedium.copy(
                    color = Color.White
                ),
                maxLines = 3
            )
        }
    }
}
