package com.bersyte.taskflow.feature.notifications.data.datasource

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.bersyte.taskflow.feature.notifications.data.models.AppNotification
import kotlinx.coroutines.flow.Flow

@Dao
interface NotificationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(notification: AppNotification)

    @Delete
    suspend fun delete(notification: AppNotification)

    @Update
    suspend fun update(notification: AppNotification)

    @Query("SELECT * FROM notifications WHERE taskId = :taskId")
    fun notificationByTaskId(taskId: Long): Flow<AppNotification?>

    @Query("SELECT * FROM notifications ORDER BY createdAt DESC")
    fun getAllNotifications(): Flow<List<AppNotification>>




}
