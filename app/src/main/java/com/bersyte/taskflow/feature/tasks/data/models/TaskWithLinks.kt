package com.bersyte.taskflow.feature.tasks.data.models

import androidx.room.Embedded
import androidx.room.Relation

data class TaskWithLinks(
    @Embedded val task: Task,
    @Relation(
        parentColumn = "taskId",
        entityColumn = "taskOwnerId"
    )
    val taskLinks: List<TaskLink>
)
