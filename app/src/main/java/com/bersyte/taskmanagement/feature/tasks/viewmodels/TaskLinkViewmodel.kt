package com.bersyte.taskmanagement.feature.tasks.viewmodels

import androidx.lifecycle.ViewModel
import com.bersyte.taskmanagement.feature.tasks.data.repositories.taskLink.TaskLinkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TaskLinkViewmodel @Inject constructor(
    private val repository: TaskLinkRepository
): ViewModel() {


}
