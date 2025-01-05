package com.bersyte.taskFlow.common.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bersyte.taskFlow.common.navigation.Route

@Composable
fun  NoTasksMessageCard(
    msg: String,
    navController: NavHostController
) {

    ThemedCard(
        onClick = {
            navController.navigate(Route.AddTask.name)
        },
        modifier = Modifier.fillMaxWidth()
            .height(120.dp),
        content = {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(msg, textAlign = TextAlign.Center)
            }
        }
    )
}
