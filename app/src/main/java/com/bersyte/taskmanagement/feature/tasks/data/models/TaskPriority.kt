package com.bersyte.taskmanagement.feature.tasks.data.models

import androidx.compose.ui.graphics.Color
import com.bersyte.taskmanagement.common.theme.LightBlue
import com.bersyte.taskmanagement.common.theme.LightGreen

enum class TaskPriority(val color: Color) {
    LOW(LightGreen),
    MEDIUM(LightBlue),
    HIGH(Color.Red)
}
