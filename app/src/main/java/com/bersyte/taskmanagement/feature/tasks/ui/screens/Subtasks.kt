package com.bersyte.taskmanagement.feature.tasks.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.key
import androidx.hilt.navigation.compose.hiltViewModel
import com.bersyte.taskmanagement.common.components.VerticalSpace
import com.bersyte.taskmanagement.feature.tasks.ui.components.AddSubTaskButton
import com.bersyte.taskmanagement.feature.tasks.ui.components.SubtaskCard
import com.bersyte.taskmanagement.feature.tasks.ui.components.SwipeToDeleteContainer
import com.bersyte.taskmanagement.feature.tasks.viewmodels.SubtaskViewmodel

@Composable
fun Subtasks(
    taskId: Long,
    subtaskVM: SubtaskViewmodel = hiltViewModel()
) {

    val subtasksState = subtaskVM.subtaskState.collectAsState()
    val subtasksStateValue = subtasksState.value

    LaunchedEffect(subtasksStateValue) {
        subtaskVM.getTaskWithSubtasks(taskId)
    }


    Column {
        when{
            subtasksStateValue.data != null -> {
                val subtasks = subtasksStateValue.data

                subtasks.forEach {subtask ->

                    key(subtask.subtaskId) {
                        SwipeToDeleteContainer(
                            content = {
                                SubtaskCard(subtask = subtask)
                            },
                            item = subtask,
                            onDelete = { subtaskToDelete ->
                                subtaskVM.deleteSubtask(subtaskToDelete)
                            }
                        )
                    }
                    VerticalSpace(16)
                }
            }
        }
        AddSubTaskButton(taskId)
        VerticalSpace(32)
    }
}
