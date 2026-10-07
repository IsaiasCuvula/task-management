package com.bersyte.taskflow.feature.profile.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bersyte.taskflow.core.ui.UiState
import com.bersyte.taskflow.feature.profile.data.models.AppUser
import com.bersyte.taskflow.feature.profile.data.repositories.IProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException


@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: IProfileRepository
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
        }catch (e: CancellationException){
            throw e
        }catch (e: Exception){
            Log.d("ProfileViewModel", "Error while getting current user - $e")
            _profileState.update {
                it.copy(isLoading = false, error = e.message)
            }
            return@launch
        }
    }

    fun updateUser(appUser: AppUser) = viewModelScope.launch {
        try {
            repository.update(appUser)
            _profileState.update {
                it.copy(data = appUser)
            }
        }catch (e: CancellationException){
            throw e
        }catch (e:Exception){
            Log.d("ProfileViewModel", "Update user error: $e")
            _profileState.update {
                it.copy(isLoading = false, error = e.message)
            }
            return@launch
        }
    }

    private fun saveUser(appUser: AppUser) = viewModelScope.launch {
        try {
            _profileState.update {it.copy(isLoading = true)}
            //
            repository.insert(appUser)
            //
            _profileState.update {it.copy(isLoading = false)}
        }catch (e: CancellationException){
            throw e
        }catch (e: Exception){
            Log.d("ProfileViewModel", "Saving user exception - ${e.message}")
            _profileState.update {
                it.copy(isLoading = false, error = e.message)
            }
            return@launch
        }
    }

}
