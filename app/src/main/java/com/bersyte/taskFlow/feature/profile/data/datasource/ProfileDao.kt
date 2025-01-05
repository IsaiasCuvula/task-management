package com.bersyte.taskFlow.feature.profile.data.datasource

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.bersyte.taskFlow.feature.profile.data.models.AppUser
import kotlinx.coroutines.flow.Flow

@Dao
interface ProfileDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(appUser: AppUser)

    @Delete
    suspend fun delete(appUser: AppUser)

    @Update
    suspend fun update(appUser: AppUser)

    @Query("SELECT * FROM user_info WHERE id = :id")
    fun getUserById(id: Int): Flow<AppUser?>

    @Query("SELECT * FROM user_info LIMIT 1")
    fun getCurrentUser():Flow<AppUser?>
}
