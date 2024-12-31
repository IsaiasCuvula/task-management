package com.bersyte.taskmanagement.feature.tasks.data.datasource

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

interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: Task)

    @Delete
    suspend fun delete(task: Task)

    @Update
    suspend fun update(task: Task)

    @Query("SELECT * FROM tasks WHERE taskId = :id")
    fun getTaskById(id: Long): Flow<Task?>

    @Query("SELECT * FROM tasks ORDER BY dueDate ASC")
    fun getAllTasks():  Flow<List<Task>>

    @Query("SELECT * FROM tasks WHERE priority = :priority ORDER BY dueDate DESC")
    fun getAllUrgentTasks(priority: TaskPriority): Flow<List<Task>>

    @Transaction
    @Query("SELECT * FROM tasks WHERE taskId = :taskId")
    fun getTasksWithSubtasks(taskId: Long): Flow<List<TaskWithSubtasks>>

    @Transaction
    @Query("SELECT * FROM tasks WHERE taskId = :taskId")
    fun getTasksWithLinks(taskId: Long): Flow<List<TaskWithLinks>>
}
