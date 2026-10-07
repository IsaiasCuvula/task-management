package com.bersyte.taskflow.feature.tasks.data.models

import androidx.room.Embedded
import androidx.room.Relation

data class TaskWithSubtasks(
    @Embedded val task: Task,
    @Relation(
        parentColumn = "taskId",
        entityColumn = "taskOwnerId"
    )
    val subtasks: List<Subtask>
)
