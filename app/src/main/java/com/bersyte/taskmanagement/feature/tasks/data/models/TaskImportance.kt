package com.bersyte.taskmanagement.feature.tasks.data.models

import androidx.compose.ui.graphics.Color
import com.bersyte.taskmanagement.common.theme.LightBlue
import com.bersyte.taskmanagement.common.theme.LightGreen
import com.bersyte.taskmanagement.common.theme.Purple

enum class TaskImportance(val color: Color) {
    LOW(LightGreen),
    MEDIUM(LightBlue),
    HIGH(Purple)
}
