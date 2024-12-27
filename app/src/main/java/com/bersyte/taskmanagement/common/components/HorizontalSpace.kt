package com.bersyte.taskmanagement.common.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HorizontalSpace(width: Int = 8) {
    Spacer( modifier = Modifier.width(width.dp))
}
