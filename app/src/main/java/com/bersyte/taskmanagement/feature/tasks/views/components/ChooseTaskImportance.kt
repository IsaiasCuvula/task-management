package com.bersyte.taskmanagement.feature.tasks.views.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bersyte.taskmanagement.feature.tasks.data.models.TaskPriority

@Composable
fun ChooseTaskImportance(
    onResponse: (TaskPriority) -> Unit,
) {
    var selectedImportance by remember {  mutableStateOf(TaskPriority.LOW) }
    val colors = MaterialTheme.colorScheme

    Row(
        modifier = Modifier.fillMaxWidth()
            .background(
                color = colors.surface,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(2.dp)
    ) {
        TaskPriority.entries.forEach { taskImportance ->
            val isSelected = selectedImportance == taskImportance
            Surface(
                onClick = {
                    selectedImportance = taskImportance
                    onResponse(selectedImportance)
                },
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.weight(1f)
            ){
                  Text(
                      taskImportance.name,
                      style = MaterialTheme.typography.titleMedium.copy(
                          color = if(isSelected) Color.White else colors.primary
                      ),
                      textAlign = TextAlign.Center,
                      modifier = Modifier
                          .background(
                              color = if(isSelected)colors.primary else Color.Transparent,
                              shape = RoundedCornerShape(16.dp)
                          )
                          .padding(16.dp)
                )
            }
        }
    }
}
