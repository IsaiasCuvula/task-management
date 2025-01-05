package com.bersyte.taskFlow.feature.notifications.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bersyte.taskFlow.core.ui.UiState
import com.bersyte.taskFlow.feature.notifications.data.models.AppNotification
import com.bersyte.taskFlow.feature.notifications.data.repositories.INotificationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val repository: INotificationRepository
): ViewModel() {

    private val _notificationsState = MutableStateFlow(UiState<List<AppNotification>>())
    val notificationsState = _notificationsState.asStateFlow()

    init {
        getAllNotifications()
    }

   private fun getAllNotifications() = viewModelScope.launch {
        _notificationsState.update { it.copy(isLoading = true) }
        try {
            repository.getAllNotifications().collect{ notifications ->
                _notificationsState.update {
                    it.copy(isLoading = false, data = notifications)
                }
            }
        } catch (e: Exception) {
            Log.d("Fetch task with notifications", "Fetch notifications error: $e")
            _notificationsState.update {
                it.copy(isLoading = false, error = e.message)
            }
        }
    }


    fun deleteNotification(notification: AppNotification)= viewModelScope.launch {
        try {
            repository.delete(notification)
        }catch (e:Exception){
            Log.d("Delete notification", "Delete notification error: $e")
            _notificationsState.update {
                it.copy(isLoading = false, error = e.message)
            }
            return@launch
        }
    }

    fun updateNotification(notification: AppNotification)= viewModelScope.launch {
        try {
            repository.update(notification)
        }catch (e:Exception){
            Log.d("Update notification", "Update notification error: $e")
            _notificationsState.update {
                it.copy(isLoading = false, error = e.message)
            }
            return@launch
        }
    }
}
