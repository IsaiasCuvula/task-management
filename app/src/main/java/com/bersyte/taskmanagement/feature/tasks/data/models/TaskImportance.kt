package com.bersyte.taskmanagement.feature.tasks.data.models

enum class TaskImportance {
    CRITICAL,   // Tasks that are urgent and require immediate attention
    HIGH,       // Tasks that are important but not urgent
    MEDIUM,     // Tasks that are moderately important
    LOW,        // Tasks that can be attended to later
    OPTIONAL    // Tasks that are nice-to-have but not necessary
}
