package com.bersyte.taskmanagement.feature.profile.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bersyte.taskmanagement.core.ui.UiState
import com.bersyte.taskmanagement.feature.profile.data.models.AppUser
import com.bersyte.taskmanagement.feature.profile.data.repositories.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: ProfileRepository
): ViewModel() {
    private val _profileState = MutableStateFlow(UiState<AppUser>())
    val profileState = _profileState.asStateFlow()

    init {
        getCurrentUser()
    }

    private fun getCurrentUser() = viewModelScope.launch {
        try {
            _profileState.update {it.copy(isLoading = true)}
            //
            repository.getCurrentUser().collect{ user ->
                if(user == null){
                    val appUser = AppUser(
                        id = 0, username = "Unknown"
                    )
                    saveUser(appUser)
                }else{
                    _profileState.update {
                        it.copy(isLoading = false, data = user)
                    }
                }

            }
        }catch (e: Exception){
            val tag = "ProfileViewModel "
            Log.d(tag, "Error while getting current user - ${e.message}")
            _profileState.update {
                it.copy(isLoading = false, error = e.message)
            }
            return@launch
        }
    }

    fun updateUser(appUser: AppUser) = viewModelScope.launch {
        try {
            repository.update(appUser)
        }catch (e:Exception){
            Log.d("Update user", "Update user error: $e")
            _profileState.update {
                it.copy(isLoading = false, error = e.message)
            }
            return@launch
        }
    }

    fun deleteUser(appUser: AppUser)= viewModelScope.launch {
        try {
            repository.delete(appUser)
        }catch (e:Exception){
            Log.d("Delete user", "Delete user error: $e")
            _profileState.update {
                it.copy(isLoading = false, error = e.message)
            }
            return@launch
        }
    }

    fun getUserById(userId: Int) = viewModelScope.launch {
        _profileState.update { it.copy(isLoading = true) }

        try {
            repository.getUserById(userId).collect{ user ->
                _profileState.update {
                    it.copy(isLoading = false, data = user)
                }
            }
        } catch (e: Exception) {
            Log.d("Fetch user by id", "Fetch user by id error: $e")
            _profileState.update {
                it.copy(isLoading = false, error = e.message)
            }
        }
    }

    private fun saveUser(appUser: AppUser) = viewModelScope.launch {
        try {
            _profileState.update {it.copy(isLoading = true)}
            //
            repository.insert(appUser)
            //
            _profileState.update {it.copy(isLoading = false)}
        }catch (e: Exception){
            val tag = "Error while saving user"
            Log.d(tag, "$tag - ${e.message}")
            _profileState.update {
                it.copy(isLoading = false, error = e.message)
            }
            return@launch
        }
    }

}
