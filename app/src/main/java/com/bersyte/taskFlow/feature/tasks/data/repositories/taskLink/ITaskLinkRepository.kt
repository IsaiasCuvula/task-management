package com.bersyte.taskFlow.feature.tasks.data.repositories.taskLink

import com.bersyte.taskFlow.feature.tasks.data.models.Subtask
import com.bersyte.taskFlow.feature.tasks.data.models.TaskLink
import kotlinx.coroutines.flow.Flow

interface ITaskLinkRepository {
    suspend fun insert(taskLink: TaskLink)
    suspend fun delete(taskLink: TaskLink)
    suspend fun update(taskLink: TaskLink)
    suspend fun getSubtasksByTaskId(taskId: Long): Flow<List<Subtask>>
}
