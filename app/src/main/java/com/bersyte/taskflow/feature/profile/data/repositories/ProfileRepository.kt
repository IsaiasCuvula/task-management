package com.bersyte.taskflow.feature.profile.data.repositories

import com.bersyte.taskflow.core.db.AppDatabase
import com.bersyte.taskflow.feature.profile.data.models.AppUser
import kotlinx.coroutines.flow.Flow

class ProfileRepository(val db: AppDatabase): IProfileRepository {
    private val dao = db.profileDao()

    override suspend fun insert(appUser: AppUser) = dao.insert(appUser)

    override suspend fun delete(appUser: AppUser) = dao.delete(appUser)

    override suspend fun update(appUser: AppUser) = dao.update(appUser)

    override suspend fun getCurrentUser():Flow<AppUser?> {
        return dao.getCurrentUser()
    }

    override suspend fun getUserById(id: Int): Flow<AppUser?> {
        return dao.getUserById(id)
    }
}
