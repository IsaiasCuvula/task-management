package com.bersyte.taskflow.feature.notifications.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bersyte.taskflow.core.ui.UiState
import com.bersyte.taskflow.feature.notifications.data.models.AppNotification
import com.bersyte.taskflow.feature.notifications.data.repositories.INotificationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val repository: INotificationRepository
): ViewModel() {

    private val _notificationsState = MutableStateFlow(UiState<List<AppNotification>>())
    val notificationsState = _notificationsState.asStateFlow()

    private val _hasNotSeenNotificationsState = MutableStateFlow(UiState<Boolean>())
    val hasNotSeenNotificationsState = _hasNotSeenNotificationsState.asStateFlow()

    init {
        getAllNotifications()
    }

   private fun getAllNotifications() = viewModelScope.launch {
        _notificationsState.update { it.copy(isLoading = true) }
        try {
            repository.getAllNotifications().collect{ notifications ->
                val hasNotSeenNotification = notifications.any {!it.isSeen}

                _notificationsState.update {
                    it.copy(isLoading = false, data = notifications)
                }

                _hasNotSeenNotificationsState.update { it.copy(hasNotSeenNotification) }
            }
        }catch (e: CancellationException){
            throw e
        } catch (e: Exception) {
            Log.d("NotificationViewModel", "Fetch notifications error: $e")
            _notificationsState.update {
                it.copy(isLoading = false, error = e.message)
            }
        }
   }


   fun deleteNotification(notification: AppNotification)= viewModelScope.launch {
       try {
           repository.delete(notification)
       }catch (e: CancellationException){
           throw e
       }catch (e:Exception){
           Log.d("NotificationViewModel", "Delete notification error: $e")
           _notificationsState.update {
               it.copy(isLoading = false, error = e.message)
           }
           return@launch
       }
   }

   fun updateNotification(notification: AppNotification)= viewModelScope.launch {
       try {
           repository.update(notification)
       }catch (e: CancellationException){
           throw e
       }catch (e:Exception){
           Log.d("NotificationViewModel", "Update notification error: $e")
           _notificationsState.update {
               it.copy(isLoading = false, error = e.message)
           }
           return@launch
       }
   }
}
