package com.bersyte.taskmanagement.feature.tasks.data.repositories.task

import com.bersyte.taskmanagement.core.data.AppDatabase
import com.bersyte.taskmanagement.feature.tasks.data.models.Task
import com.bersyte.taskmanagement.feature.tasks.data.models.TaskPriority
import com.bersyte.taskmanagement.feature.tasks.data.models.TaskWithLinks
import com.bersyte.taskmanagement.feature.tasks.data.models.TaskWithSubtasks
import kotlinx.coroutines.flow.Flow

class TaskRepository(db: AppDatabase): ITaskRepository {
    private val dao = db.taskDao()

    override suspend fun insert(task: Task) = dao.insert(task)

    override suspend fun delete(task: Task) = dao.delete(task)

    override suspend fun update(task: Task) = dao.update(task)

    override suspend fun getTaskById(id: Long): Flow<TaskWithSubtasks?> {
        return dao.getTaskById(id)
    }

    override suspend fun getTasksExcludingHighPriority(): Flow<List<TaskWithSubtasks>> {
        return dao.getTasksExcludingHighPriority()
    }

    override suspend fun getTasksByPriority(priority: TaskPriority): Flow<List<TaskWithSubtasks>> {
        return dao.getTasksByPriority(priority)
    }

    override suspend fun getTasksWithSubtasks(taskId: Long): Flow<TaskWithSubtasks> {
        return dao.getTasksWithSubtasks(taskId)
    }

    override suspend fun getAllTasksWithSubtasks(): Flow<List<TaskWithSubtasks>> {
        return dao.getAllTasksWithSubtasks()
    }

    override suspend fun getTasksWithLinks(taskId: Long): Flow<TaskWithLinks> {
        return dao.getTasksWithLinks(taskId)
    }

    override suspend fun getTasksByDueDate(dueDate: String): Flow<List<TaskWithSubtasks>> {
        return dao.getTasksByDueDate(dueDate)
    }
}
