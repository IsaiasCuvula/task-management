package com.bersyte.taskmanagement.feature.tasks.data.repositories.task

import com.bersyte.taskmanagement.feature.tasks.data.models.Task
import com.bersyte.taskmanagement.feature.tasks.data.models.TaskPriority
import com.bersyte.taskmanagement.feature.tasks.data.models.TaskWithLinks
import com.bersyte.taskmanagement.feature.tasks.data.models.TaskWithSubtasks
import kotlinx.coroutines.flow.Flow

interface ITaskRepository {
    suspend fun insert(task: Task)
    suspend fun delete(task: Task)
    suspend fun update(task: Task)
    suspend fun getTaskById(id: Long): Flow<Task?>
    suspend fun getAllTasks(): Flow<List<Task>>
    suspend fun getTasksByPriority(priority: TaskPriority): Flow<List<Task>>
    suspend fun getTasksWithSubtasks(taskId: Long): Flow<List<TaskWithSubtasks>>
    suspend fun getTasksWithLinks(taskId: Long): Flow<List<TaskWithLinks>>
}
