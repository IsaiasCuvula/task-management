package com.bersyte.taskflow.feature.tasks.ui.screens

import android.Manifest
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.bersyte.taskflow.common.components.BackButton
import com.bersyte.taskflow.common.components.LoadingIndicator
import com.bersyte.taskflow.common.components.ShowErrorMessage
import com.bersyte.taskflow.common.components.VerticalSpace
import com.bersyte.taskflow.feature.tasks.ui.components.DetailsHeader
import com.bersyte.taskflow.feature.tasks.ui.components.FileAndLinks
import com.bersyte.taskflow.feature.tasks.ui.components.TaskProgressBar
import com.bersyte.taskflow.feature.tasks.viewmodels.TaskViewmodel

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
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


    var canShowNotification by remember { mutableStateOf(false) }
    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        canShowNotification = it
    }

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

                if(task.isCompleted){
                    permissionLauncher.launch(
                        Manifest.permission.POST_NOTIFICATIONS
                    )
                }

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
                            TaskProgressBar(
                                height = 12,
                                percentage = task.percentageCompleted,
                                color = colors.tertiary,
                                trackColor = colors.surface,
                                fontSize = MaterialTheme.typography.titleMedium.fontSize
                            )
                            VerticalSpace(16)
                            Text(
                                "Sub tasks",
                                style = textStyle.titleMedium.copy(
                                    fontWeight = FontWeight.Bold
                                )
                            )
                            VerticalSpace(16)
                            Subtasks(taskId = task.taskId)
                        }
                    }
                }
            }
        }
    }
}
