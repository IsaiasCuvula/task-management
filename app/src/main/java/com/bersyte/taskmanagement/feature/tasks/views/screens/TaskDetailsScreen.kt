package com.bersyte.taskmanagement.feature.tasks.views.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccessTime
import androidx.compose.material.icons.rounded.AddLink
import androidx.compose.material.icons.rounded.AttachFile
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bersyte.taskmanagement.common.components.BackButton
import com.bersyte.taskmanagement.common.components.EditTaskButton
import com.bersyte.taskmanagement.common.components.HorizontalSpace
import com.bersyte.taskmanagement.common.components.ProgressBar
import com.bersyte.taskmanagement.common.components.ThemedCard
import com.bersyte.taskmanagement.common.components.VerticalSpace
import com.bersyte.taskmanagement.feature.tasks.views.components.AddSubTask
import com.bersyte.taskmanagement.feature.tasks.views.components.SwipeToDeleteContainer
import kotlinx.coroutines.launch

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

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded  = true
    )
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

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
            Column(
                modifier = Modifier.fillMaxWidth()
                    .background(
                        color = colors.primary,
                        shape = RoundedCornerShape(
                            bottomEnd = 16.dp, bottomStart = 16.dp
                        )
                    )
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Task title")
                    EditTaskButton(navController)
                }
                VerticalSpace(4)
                Text(
                    "Landing page design",
                    style = textStyle.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                    ),
                    maxLines = 1
                )
                VerticalSpace(16)
                Text("Due date")
                VerticalSpace(6)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row {
                        Icon(
                            Icons.Rounded.AccessTime,
                            contentDescription = "Clock",
                        )
                        HorizontalSpace(8)
                        Text(
                            "2 pm - 3 pm",
                            style = textStyle.bodyLarge
                        )
                    }
                    Row {
                        Icon(
                            Icons.Rounded.CalendarMonth,
                            contentDescription = "Calendar",
                        )
                        HorizontalSpace(8)
                        Text(
                            "29 May 2024",
                            style = textStyle.bodyLarge
                        )
                    }
                }
                VerticalSpace(16)
            }
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
                    VerticalSpace(16)
                    ThemedCard(
                        content = {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text("File & Links: ")
                                HorizontalSpace(16)
                                Icon(
                                    Icons.Rounded.AttachFile,
                                    contentDescription = "",
                                    modifier = Modifier.size(30.dp),
                                )
                                Icon(
                                    Icons.Rounded.AddLink,
                                    contentDescription = "",
                                    modifier = Modifier.size(30.dp),
                                )
                            }
                        }
                    )
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

                items(
                    items = subTasks,
                    key = { it }
                ){ subTask ->

                    SwipeToDeleteContainer(
                        content = {
                            ThemedCard(
                                modifier = Modifier.fillMaxWidth(),
                                contentPadding = 8,
                                content = {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                    ) {
                                        Checkbox(
                                            checked = true,
                                            onCheckedChange = { isChecked ->
                                                // Update the individual child state
                                                // childCheckedStates[index] = isChecked
                                            },
                                            colors = CheckboxDefaults.colors(
                                                checkedColor = colors.onTertiary
                                            ),
                                        )
                                        Text(
                                            subTask,
                                            style = textStyle.titleMedium.copy(
                                                textDecoration = TextDecoration.LineThrough
                                            ),
                                            modifier = Modifier.weight(1f)
                                        )
                                    }

                                }
                            )
                        },
                        item = subTask,
                        onDelete = { subtaskToDelete ->
                            subTasks.remove(subtaskToDelete)
                        },
                    )

                    VerticalSpace(16)
                }
                 item{
                     OutlinedButton(
                         modifier = Modifier.fillMaxWidth()
                             .height(50.dp),
                         shape = RoundedCornerShape(16.dp),
                         onClick = {showBottomSheet = true}
                     ) {
                         Text(
                             "Add a subtask",
                             color = Color.White,
                             style =   textStyle.titleMedium.copy(
                                 fontWeight = FontWeight.SemiBold
                             )
                         )
                     }
                     VerticalSpace(32)

                     if(showBottomSheet){
                         ModalBottomSheet(
                             sheetState = sheetState,
                             modifier = Modifier.fillMaxWidth()
                                 .heightIn(
                                     max = LocalConfiguration.current.screenHeightDp.dp * 0.9f
                                 ),
                             onDismissRequest = {
                                 showBottomSheet = false
                             },
                             dragHandle = {}
                         ) {
                             AddSubTask (
                                 onClose = {
                                     scope.launch { sheetState.hide() }
                                         .invokeOnCompletion {
                                             if(!sheetState.isVisible){
                                                 showBottomSheet = false
                                             }
                                         }
                                 }
                             )
                         }
                     }
                 }
            }
        }
    }
}
