package com.bersyte.taskFlow.feature.tasks.data.models

import androidx.compose.ui.graphics.Color
import com.bersyte.taskFlow.common.theme.LightBlue
import com.bersyte.taskFlow.common.theme.LightGreen

enum class TaskPriority(val color: Color) {
    LOW(LightGreen),
    MEDIUM(LightBlue),
    HIGH(Color.Red)
}
