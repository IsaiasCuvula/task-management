package com.bersyte.taskFlow.feature.tasks.data.repositories.subtask

import com.bersyte.taskFlow.core.db.AppDatabase
import com.bersyte.taskFlow.feature.tasks.data.models.Subtask
import kotlinx.coroutines.flow.Flow

class SubtaskRepository(db: AppDatabase): ISubtaskRepository {
    private val dao = db.subtaskDao()

    override suspend fun insert(subtask: Subtask) = dao.insert(subtask)

    override suspend fun delete(subtask: Subtask)= dao.delete(subtask)

    override suspend fun update(subtask: Subtask)= dao.update(subtask)

    override suspend fun getSubtasksByTaskId(taskId: Long): Flow<List<Subtask>> {
       return dao.getSubtasksByTaskId(taskId)
    }
}
