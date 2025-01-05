package com.bersyte.taskFlow.feature.tasks.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bersyte.taskFlow.feature.tasks.data.models.TaskPriority

@Composable
fun SelectTaskPriority(
    initialPriority: TaskPriority = TaskPriority.LOW,
    onResponse: (TaskPriority) -> Unit,
) {

    var selectedPriority by remember {mutableStateOf(initialPriority)}
    val colors = MaterialTheme.colorScheme

    // Update selectedPriority when initialPriority changes
    LaunchedEffect(initialPriority) {
        selectedPriority = initialPriority
    }

    Row(
        modifier = Modifier.fillMaxWidth()
            .background(
                color = colors.surface,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(2.dp)
    ) {
        TaskPriority.entries.forEach { taskPriority ->
            val isSelected = selectedPriority == taskPriority
            Surface(
                onClick = {
                    selectedPriority = taskPriority
                    onResponse(selectedPriority)
                },
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.weight(1f)
            ){
                  Text(
                      taskPriority.name,
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
