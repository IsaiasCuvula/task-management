package com.bersyte.taskmanagement.feature.tasks.views.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import com.bersyte.taskmanagement.common.components.ThemedCard

@Composable
fun SubtaskCard(subTask: String) {

    ThemedCard(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = 8,
        content = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Checkbox(
                    checked = true,
                    onCheckedChange = { isChecked ->
                        // Update the individual child state
                        // childCheckedStates[index] = isChecked
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = MaterialTheme.colorScheme.onTertiary
                    ),
                )
                Text(
                    subTask,
                    style = MaterialTheme.typography.titleMedium.copy(
                        textDecoration = TextDecoration.LineThrough
                    ),
                    modifier = Modifier.weight(1f)
                )
            }

        }
    )
}
