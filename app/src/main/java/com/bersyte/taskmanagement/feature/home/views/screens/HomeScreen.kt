package com.bersyte.taskmanagement.feature.home.views.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bersyte.taskmanagement.common.components.CircularProgressBar
import com.bersyte.taskmanagement.common.components.ThemedCard
import com.bersyte.taskmanagement.common.components.VerticalSpace
import com.bersyte.taskmanagement.feature.home.views.components.HomeAppBar
import com.bersyte.taskmanagement.feature.home.views.components.HomeTaskCard
import com.bersyte.taskmanagement.feature.home.views.components.HomeThreeTasksCards
import com.bersyte.taskmanagement.feature.home.views.components.HomeTwoTasksCards
import com.bersyte.taskmanagement.feature.home.views.components.SearchField
import com.bersyte.taskmanagement.feature.home.views.components.UrgentTasks

@Composable
fun HomeScreen(navController: NavHostController) {

    var query by remember { mutableStateOf("") }
    val textStyle = MaterialTheme.typography
    val tasks = remember {
        mutableStateListOf("Website frontend", "Website backend", "London")
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {HomeAppBar(navController)}
    ) { innerPadding ->
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
                    ThemedCard(
                        modifier = Modifier.fillMaxWidth(),
                        content = {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column {
                                    Text(
                                        "Task Progress",
                                        style = textStyle.titleLarge.copy(
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                    VerticalSpace(6)
                                    Text(
                                        "30/40 task done",
                                        style = textStyle.bodyMedium.copy(
                                            color = Color.White.copy(alpha = 0.6f)
                                        )
                                    )
                                    VerticalSpace(10)
                                    Text(
                                        "December 27",
                                        modifier = Modifier
                                            .background(
                                                color = colorScheme.onSecondary,
                                                shape = RoundedCornerShape(16.dp)
                                            )
                                            .padding(horizontal = 10.dp)
                                    )
                                }
                                CircularProgressBar(percentage = 0.8f)
                            }
                        }
                    )
                    VerticalSpace(16)
                    when (tasks.size) {
                        1 -> {
                            HomeTaskCard()
                        }
                        2 -> {
                            HomeTwoTasksCards()
                        }
                        else -> {
                            HomeThreeTasksCards()
                        }
                    }
                    VerticalSpace(24)
                    UrgentTasks(tasks)
                }
            }
        }
    }

}
