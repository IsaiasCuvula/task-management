package com.bersyte.taskflow.feature.tasks.data.repositories.taskLink

import com.bersyte.taskflow.core.db.AppDatabase
import com.bersyte.taskflow.feature.tasks.data.models.Subtask
import com.bersyte.taskflow.feature.tasks.data.models.TaskLink
import kotlinx.coroutines.flow.Flow

class TaskLinkRepository(db: AppDatabase): ITaskLinkRepository {
    private val dao = db.taskLinkDao()

    override suspend fun insert(taskLink: TaskLink) = dao.insert(taskLink)

    override suspend fun delete(taskLink: TaskLink) = dao.delete(taskLink)

    override suspend fun update(taskLink: TaskLink)= dao.update(taskLink)

    override suspend fun getSubtasksByTaskId(taskId: Long): Flow<List<Subtask>> {
        return dao.getSubtasksByTaskId(taskId)
    }
}
