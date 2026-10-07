package com.bersyte.taskflow.feature.tasks.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.hilt.navigation.compose.hiltViewModel
import com.bersyte.taskflow.common.components.ThemedCard
import com.bersyte.taskflow.feature.tasks.data.models.Subtask
import com.bersyte.taskflow.feature.tasks.viewmodels.SubtaskViewmodel

@Composable
fun SubtaskCard(
    taskId: Long,
    subtask: Subtask,
    subtaskViewmodel: SubtaskViewmodel = hiltViewModel()
) {

    var isCompleted by remember { mutableStateOf(subtask.isCompleted) }

    LaunchedEffect(subtask) {
        isCompleted = subtask.isCompleted
    }


    ThemedCard(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = 8,
        onClick = {
            val newValue = !isCompleted
            //update ui
            isCompleted = newValue
            //save in db
            subtask.isCompleted = newValue
            subtaskViewmodel.updateSubtask(subtask, taskId)
        },
        content = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Checkbox(
                    checked = isCompleted,
                    onCheckedChange = { isChecked ->
                        //update ui
                        isCompleted = isChecked
                        //save in db
                        subtask.isCompleted = isChecked
                        subtaskViewmodel.updateSubtask(subtask, taskId)
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = MaterialTheme.colorScheme.onTertiary
                    ),
                )
                Text(
                    subtask.title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        textDecoration = if(isCompleted) TextDecoration.LineThrough
                        else TextDecoration.None
                    ),
                    modifier = Modifier.weight(1f)
                )
            }

        }
    )
}
