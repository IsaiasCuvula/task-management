package com.bersyte.taskmanagement.feature.tasks.views.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bersyte.taskmanagement.common.components.BackButton
import com.bersyte.taskmanagement.common.components.ProgressBar
import com.bersyte.taskmanagement.common.components.VerticalSpace
import com.bersyte.taskmanagement.feature.tasks.views.components.AddSubTaskButton
import com.bersyte.taskmanagement.feature.tasks.views.components.DetailsHeader
import com.bersyte.taskmanagement.feature.tasks.views.components.FileAndLinks
import com.bersyte.taskmanagement.feature.tasks.views.components.SubtaskCard
import com.bersyte.taskmanagement.feature.tasks.views.components.SwipeToDeleteContainer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailsScreen(navController: NavHostController) {

    val colors = MaterialTheme.colorScheme
    val textStyle = MaterialTheme.typography

    val subTasks = remember {
        mutableStateListOf(
            "Create wireframe all screen",
            "Make design system",
            "Design landing page"
        )
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
        Column(
            modifier =  Modifier.padding(innerPadding)
        ) {
            DetailsHeader(navController)
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
                        Text("Task landing page design concept. This project will be" +
                                " Designed a basic for bringing up as " +
                                "digital self-care that helps you reach",

                            modifier = Modifier
                                .border(
                                    border = BorderStroke(1.dp, color = colors.surface),
                                    shape = RoundedCornerShape(16.dp)
                                )
                                .padding(16.dp)
                        )
                    }
                    VerticalSpace(16)
                    FileAndLinks()
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
                }

                items(items = subTasks, key = { it }){ subTask ->
                    SwipeToDeleteContainer(
                        content = {
                            SubtaskCard(subTask = subTask)
                        },
                        item = subTask,
                        onDelete = { subtaskToDelete ->
                            subTasks.remove(subtaskToDelete)
                        }
                    )
                    VerticalSpace(16)
                }
                item{
                    AddSubTaskButton()
                }
            }
        }
    }
}
