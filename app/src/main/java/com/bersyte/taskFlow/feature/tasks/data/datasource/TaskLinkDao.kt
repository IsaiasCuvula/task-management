package com.bersyte.taskFlow.feature.tasks.data.datasource

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.bersyte.taskFlow.feature.tasks.data.models.Subtask
import com.bersyte.taskFlow.feature.tasks.data.models.TaskLink
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskLinkDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(taskLink: TaskLink)

    @Delete
    suspend fun delete(taskLink: TaskLink)

    @Update
    suspend fun update(taskLink: TaskLink)

    @Query("SELECT * FROM subtasks WHERE taskOwnerId = :taskId ORDER BY createdAt DESC")
    fun getSubtasksByTaskId(taskId: Long): Flow<List<Subtask>>
}
