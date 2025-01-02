package com.bersyte.taskmanagement.feature.tasks.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.bersyte.taskmanagement.common.components.BackButton
import com.bersyte.taskmanagement.common.components.LoadingIndicator
import com.bersyte.taskmanagement.common.components.ProgressBar
import com.bersyte.taskmanagement.common.components.ShowErrorMessage
import com.bersyte.taskmanagement.common.components.VerticalSpace
import com.bersyte.taskmanagement.feature.tasks.ui.components.DetailsHeader
import com.bersyte.taskmanagement.feature.tasks.ui.components.FileAndLinks
import com.bersyte.taskmanagement.feature.tasks.viewmodels.TaskViewmodel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailsScreen(
    taskId: Long,
    navController: NavHostController,
    taskViewmodel: TaskViewmodel = hiltViewModel()
) {

    val taskByIdState = taskViewmodel.taskByIdState.collectAsState()
    val taskByIdStateValue = taskByIdState.value

    LaunchedEffect(taskByIdState) {
        taskViewmodel.getTaskById(taskId)
    }

    val colors = MaterialTheme.colorScheme
    val textStyle = MaterialTheme.typography

    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colors.primary
                ),
                navigationIcon = {
                    BackButton(navController)
                },
                title = {
                    Text("Project details")
                }
            )
        }
    ){ innerPadding ->

        when{
            taskByIdStateValue.isLoading -> {
                LoadingIndicator()
            }
            taskByIdStateValue.error != null -> {
                ShowErrorMessage(taskByIdStateValue.error)
            }
            taskByIdStateValue.data != null -> {
                val task = taskByIdStateValue.data

                Column(
                    modifier =  Modifier.padding(innerPadding)
                ) {
                    DetailsHeader(
                        navController = navController,
                        task = task
                    )
                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                            .padding(16.dp)
                    ) {
                        item {
                            Text(
                                "Descriptions",
                                style = textStyle.titleLarge.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                ),
                            )
                            VerticalSpace(16)
                            SelectionContainer {
                                Text(
                                    task.description,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .border(
                                            border = BorderStroke(1.dp, color = colors.surface),
                                            shape = RoundedCornerShape(16.dp)
                                        )
                                        .padding(16.dp)
                                )
                            }
                            VerticalSpace(16)
                            FileAndLinks(navController = navController, taskId = taskId)
                            VerticalSpace(32)
                            ProgressBar(
                                percentage = 0.7f, height = 12,
                                fontSize = textStyle.titleMedium.fontSize,
                                color = colors.tertiary,
                                trackColor = colors.surface

                            )
                            VerticalSpace(16)
                            Text(
                                "Sub tasks",
                                style = textStyle.titleMedium.copy(
                                    fontWeight = FontWeight.Bold
                                )
                            )
                            VerticalSpace(16)
                            Subtasks(taskId = taskId)
                        }
                    }
                }
            }
        }
    }
}
