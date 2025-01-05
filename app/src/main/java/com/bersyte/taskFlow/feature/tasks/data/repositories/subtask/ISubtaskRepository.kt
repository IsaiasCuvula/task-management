package com.bersyte.taskFlow.feature.tasks.data.repositories.subtask

import com.bersyte.taskFlow.feature.tasks.data.models.Subtask
import kotlinx.coroutines.flow.Flow

interface ISubtaskRepository {

    suspend fun insert(subtask: Subtask)

    suspend fun delete(subtask: Subtask)

    suspend fun update(subtask: Subtask)

    suspend fun getSubtasksByTaskId(taskId: Long): Flow<List<Subtask>>
}
