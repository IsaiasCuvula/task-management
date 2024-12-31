package com.bersyte.taskmanagement.feature.home.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bersyte.taskmanagement.common.components.VerticalSpace
import com.bersyte.taskmanagement.common.navigation.NavigationDrawer
import com.bersyte.taskmanagement.feature.home.ui.components.HomeOneTaskCard
import com.bersyte.taskmanagement.feature.home.ui.components.HomeThreeTasksCards
import com.bersyte.taskmanagement.feature.home.ui.components.HomeTwoTasksCards
import com.bersyte.taskmanagement.feature.home.ui.components.SearchField
import com.bersyte.taskmanagement.feature.home.ui.components.TasksCompleted
import com.bersyte.taskmanagement.feature.home.ui.components.UrgentTasks

@Composable
fun HomeScreen(navController: NavHostController) {

    var query by remember { mutableStateOf("") }
    val textStyle = MaterialTheme.typography
    val tasks = remember {
        mutableStateListOf("Website frontend", "Website backend" ,"London")
    }

    NavigationDrawer(
        navController = navController,
        content = { innerPadding ->
            LazyColumn {
                item {
                    Column(
                        modifier = Modifier.fillMaxSize()
                            .padding(innerPadding)
                            .padding(horizontal =  16.dp),
                        verticalArrangement = Arrangement.Top
                    ) {
                        Text("Hi, Jason")
                        Text(
                            "Be, productive today",
                            style = textStyle.titleLarge.copy(
                                fontWeight = FontWeight.Bold
                            )
                        )
                        VerticalSpace(24)
                        SearchField(
                            query = query,
                            onQueryChanged = {newValue ->
                                query = newValue
                            },
                            onQueryClear = {query = ""},
                        )
                        VerticalSpace(16)
                        TasksCompleted(navController)
                        VerticalSpace(16)
                        when (tasks.size) {
                            1 -> {
                                HomeOneTaskCard(navController)
                            }
                            2 -> {
                                HomeTwoTasksCards(navController)
                            }
                            else -> {
                                HomeThreeTasksCards(navController)
                            }
                        }
                        VerticalSpace(24)
                        UrgentTasks(navController, tasks)
                    }
                }
            }
        }
    )
}
