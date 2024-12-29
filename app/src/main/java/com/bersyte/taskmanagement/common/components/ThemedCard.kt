package com.bersyte.taskmanagement.common.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ThemedCard(
    onClick: () -> Unit = {},
    content: @Composable ()-> Unit,
    modifier: Modifier = Modifier,
    bgColor: Color = MaterialTheme.colorScheme.surface,
    contentPadding : Int = 16,
) {

    Card(
        onClick = onClick,
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = bgColor),
    ) {
        Box(
            modifier = Modifier.padding(contentPadding.dp)
        ) {
            content()
        }
    }
}
