package com.bersyte.taskmanagement.feature.tasks.data.datasource

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.bersyte.taskmanagement.feature.tasks.data.models.Subtask
import kotlinx.coroutines.flow.Flow

interface SubtaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(subtask: Subtask)

    @Delete
    suspend fun delete(subtask: Subtask)

    @Update
    suspend fun update(subtask: Subtask)

    @Query("SELECT * FROM subtasks WHERE taskOwnerId = :taskId")
    fun getSubtasksByTaskId(taskId: Long): Flow<List<Subtask>>
}
