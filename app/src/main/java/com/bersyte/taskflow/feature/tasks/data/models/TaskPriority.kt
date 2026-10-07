package com.bersyte.taskflow.feature.tasks.data.models

import androidx.compose.ui.graphics.Color
import com.bersyte.taskflow.common.theme.LightBlue
import com.bersyte.taskflow.common.theme.LightGreen

enum class TaskPriority(val color: Color) {
    LOW(LightGreen),
    MEDIUM(LightBlue),
    HIGH(Color.Red)
}
