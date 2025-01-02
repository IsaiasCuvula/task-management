package com.bersyte.taskmanagement.feature.home.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import com.bersyte.taskmanagement.common.components.EditTaskButton

@Composable
fun HomeTaskCardTop(
    navController: NavHostController,
    title: String,
    desc: String = "",
    taskId: Long,
    descMaxLines: Int = 3
) {
    val textStyle = MaterialTheme.typography

    Column {
        EditTaskButton(
            navController = navController,
            taskId = taskId
        )
        Text(
            title,
            style = textStyle.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                color = Color.White
            ),
            maxLines = 1
        )
        if(desc.isNotEmpty()){
            Text(
                desc,
                style = textStyle.bodyMedium.copy(
                    color = Color.White
                ),
                maxLines = descMaxLines
            )
        }
    }
}
