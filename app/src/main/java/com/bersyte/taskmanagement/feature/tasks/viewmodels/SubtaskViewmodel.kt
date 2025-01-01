package com.bersyte.taskmanagement.feature.tasks.viewmodels

import androidx.lifecycle.ViewModel
import com.bersyte.taskmanagement.feature.tasks.data.repositories.subtask.SubtaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SubtaskViewmodel @Inject constructor(
    private val repository: SubtaskRepository
): ViewModel() {


}
