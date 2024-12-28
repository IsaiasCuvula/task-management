package com.bersyte.taskmanagement.feature.tasks.data.models

import androidx.compose.ui.graphics.Color

enum class TaskImportance(color: Color) {
    CRITICAL(Color.Red),   // Tasks that are urgent and require immediate attention
    HIGH(Color.Red),       // Tasks that are important but not urgent
    MEDIUM(Color.Red),     // Tasks that are moderately important
    LOW(Color.Red),        // Tasks that can be attended to later
    OPTIONAL(Color.Red)    // Tasks that are nice-to-have but not necessary
}
