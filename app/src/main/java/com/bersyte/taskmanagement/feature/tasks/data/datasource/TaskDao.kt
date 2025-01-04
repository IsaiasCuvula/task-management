package com.bersyte.taskmanagement.feature.tasks.data.datasource

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.bersyte.taskmanagement.feature.tasks.data.models.Task
import com.bersyte.taskmanagement.feature.tasks.data.models.TaskPriority
import com.bersyte.taskmanagement.feature.tasks.data.models.TaskWithLinks
import com.bersyte.taskmanagement.feature.tasks.data.models.TaskWithSubtasks
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: Task)

    @Delete
    suspend fun delete(task: Task)

    @Update
    suspend fun update(task: Task)

    @Query("SELECT * FROM tasks WHERE taskId = :id")
    fun getTaskById(id: Long): Flow<Task?>

    @Query("SELECT * FROM tasks WHERE priority != :priority ORDER BY dueDate ASC")
    fun getTasksExcludingHighPriority(priority: TaskPriority = TaskPriority.HIGH):  Flow<List<TaskWithSubtasks>>

    @Query("SELECT * FROM tasks WHERE priority = :priority ORDER BY dueDate ASC")
    fun getTasksByPriority(priority: TaskPriority): Flow<List<TaskWithSubtasks>>

    @Transaction
    @Query("SELECT * FROM tasks ORDER BY dueDate ASC")
    fun getAllTasksWithSubtasks(): Flow<List<TaskWithSubtasks>>

    @Transaction
    @Query("SELECT * FROM tasks WHERE taskId = :taskId")
    fun getTasksWithSubtasks(taskId: Long): Flow<TaskWithSubtasks>

    @Transaction
    @Query("SELECT * FROM tasks WHERE taskId = :taskId")
    fun getTasksWithLinks(taskId: Long): Flow<TaskWithLinks>
}
