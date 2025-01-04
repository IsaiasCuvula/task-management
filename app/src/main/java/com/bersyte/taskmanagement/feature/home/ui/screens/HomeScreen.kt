package com.bersyte.taskmanagement.feature.home.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.bersyte.taskmanagement.common.components.LoadingIndicator
import com.bersyte.taskmanagement.common.components.ShowErrorMessage
import com.bersyte.taskmanagement.common.components.VerticalSpace
import com.bersyte.taskmanagement.common.navigation.NavigationDrawer
import com.bersyte.taskmanagement.feature.home.ui.components.HomeOneTaskCard
import com.bersyte.taskmanagement.feature.home.ui.components.HomeThreeTasksCards
import com.bersyte.taskmanagement.feature.home.ui.components.HomeTwoTasksCards
import com.bersyte.taskmanagement.common.components.SearchField
import com.bersyte.taskmanagement.common.components.SearchTaskResult
import com.bersyte.taskmanagement.feature.home.ui.components.TasksCompleted
import com.bersyte.taskmanagement.feature.home.ui.components.UrgentTasks
import com.bersyte.taskmanagement.feature.home.viewmodels.HomeViewModel
import com.bersyte.taskmanagement.feature.profile.ui.components.HomeUsername

@Composable
fun HomeScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val homeState = homeViewModel.homeState.collectAsState()
    val homeStateValue = homeState.value

    var query by remember { mutableStateOf("") }
    val textStyle = MaterialTheme.typography

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
                        HomeUsername()
                        Text(
                            "Be, productive today",
                            style = textStyle.titleLarge.copy(
                                fontWeight = FontWeight.Bold
                            )
                        )
                        VerticalSpace(24)
                        SearchField(
                            query = query,
                            onQueryChanged = {query = it},
                            onQueryClear = {query = ""},
                            onSpeaking = {query = it}
                        )
                        when {
                            homeStateValue.isLoading ->{
                                LoadingIndicator()
                            }

                            homeStateValue.error != null -> {
                                ShowErrorMessage(homeStateValue.error)
                            }
                            homeStateValue.data != null -> {
                                val tasks = homeStateValue.data

                                if (query.isNotEmpty()){
                                    VerticalSpace(24)
                                    SearchTaskResult(
                                        query= query,
                                        allTask =  tasks,
                                        navController = navController
                                    )
                                }else{
                                    VerticalSpace(16)
                                    TasksCompleted(navController)
                                    VerticalSpace(16)
                                    when (tasks.size) {
                                        1 -> {
                                            HomeOneTaskCard(
                                                tasks = tasks,
                                                navController= navController
                                            )
                                        }
                                        2 -> {
                                            HomeTwoTasksCards(
                                                tasks = tasks,
                                                navController= navController
                                            )
                                        }
                                        else -> {
                                            if(tasks.size >=3){
                                                HomeThreeTasksCards(
                                                    tasks = tasks,
                                                    navController= navController
                                                )
                                            }
                                        }
                                    }
                                    VerticalSpace(24)
                                    UrgentTasks(navController)
                                    VerticalSpace(24)
                                }
                            }
                        }
                    }
                }
            }
        }
    )
}
