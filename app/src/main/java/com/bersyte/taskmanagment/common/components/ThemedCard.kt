package com.bersyte.taskmanagment.common.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ThemedCard(
    onClick: () -> Unit = {},
    content: @Composable ()-> Unit,
    modifier: Modifier = Modifier
) {

    Card(
        onClick = onClick,
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
    ) {
        Box(
            modifier = Modifier.padding(16.dp)
        ) {
            content()
        }
    }
}
