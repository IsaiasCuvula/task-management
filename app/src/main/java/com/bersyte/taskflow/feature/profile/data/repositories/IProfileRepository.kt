package com.bersyte.taskflow.feature.profile.data.repositories


import com.bersyte.taskflow.feature.profile.data.models.AppUser
import kotlinx.coroutines.flow.Flow

interface IProfileRepository {

    suspend fun insert(appUser: AppUser)
    suspend fun delete(appUser: AppUser)
    suspend fun update(appUser: AppUser)
    suspend fun getUserById(id: Int): Flow<AppUser?>
    suspend fun getCurrentUser():Flow<AppUser?>
}
