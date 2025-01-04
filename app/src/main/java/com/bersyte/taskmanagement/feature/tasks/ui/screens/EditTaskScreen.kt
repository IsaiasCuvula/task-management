package com.bersyte.taskmanagement.feature.tasks.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccessTime
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.bersyte.taskmanagement.common.components.BackButton
import com.bersyte.taskmanagement.common.components.CommonTextField
import com.bersyte.taskmanagement.common.components.HorizontalSpace
import com.bersyte.taskmanagement.common.components.ShowAlertDialog
import com.bersyte.taskmanagement.common.components.VerticalSpace
import com.bersyte.taskmanagement.common.navigation.Route
import com.bersyte.taskmanagement.feature.tasks.data.models.TaskPriority
import com.bersyte.taskmanagement.feature.tasks.ui.components.SelectTaskPriority
import com.bersyte.taskmanagement.feature.tasks.ui.components.ShowDatePickerDialog
import com.bersyte.taskmanagement.feature.tasks.ui.components.ShowTimePickerDialog
import com.bersyte.taskmanagement.feature.tasks.viewmodels.TaskViewmodel
import com.bersyte.taskmanagement.utils.AppHelper

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTaskScreen(
    taskId: Long,
    navController: NavHostController,
    taskViewmodel: TaskViewmodel = hiltViewModel()
) {

    val taskByIdState = taskViewmodel.taskByIdState.collectAsState()
    val taskByIdStateValue = taskByIdState.value

    LaunchedEffect(Unit) {
        taskViewmodel.getTaskById(taskId)
    }


    val today = AppHelper.getCurrentDate()
    var title by remember {  mutableStateOf("") }
    var description by remember {  mutableStateOf("") }
    var dueDate by remember {  mutableStateOf(today.date) }
    var dueTime by remember {  mutableStateOf(today.time) }
    var priority by remember {  mutableStateOf(TaskPriority.LOW) }
    val taskMin = if(dueTime.minute == 0) "00" else dueTime.minute

    var showTimePicker by remember { mutableStateOf(false) }
    var showDatePicker by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val openAlertDialog = remember {  mutableStateOf(false) }

    val focusManager = LocalFocusManager.current

    //assign initial value
    LaunchedEffect(taskByIdStateValue) {
        val data = taskByIdStateValue.data

        title = data?.title ?: ""
        description = data?.description ?:""
        dueDate = data?.dueDate ?: today.date
        dueTime = data?.dueTime ?: today.time
        priority = data?.priority ?: TaskPriority.LOW
    }


    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Edit task") },
                navigationIcon = { BackButton(navController) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                ),
                actions = {
                    IconButton(
                        onClick = {openAlertDialog.value = true}
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Delete,
                            contentDescription = ""
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
        ){
            item{
                VerticalSpace(16)
                Column {
                    Text("Title")
                    VerticalSpace(8)
                    CommonTextField(
                        value = title,
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = {
                                focusManager.moveFocus(FocusDirection.Down)
                            }
                        ),
                        onValueChange = { newValue ->
                            title = newValue
                        },
                        placeholder = "Enter task title"
                    )
                    VerticalSpace(16)
                    Text("Description")
                    VerticalSpace(8)
                    CommonTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp),
                        value = description,
                        onValueChange = { newValue ->
                            description = newValue
                        },
                        maxLines = 5,
                        placeholder = "Enter task description"
                    )
                    VerticalSpace(16)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text("Due date")
                            VerticalSpace(8)
                            Surface(
                                onClick = {
                                    showDatePicker = true
                                },
                                shape = RoundedCornerShape(16.dp)
                            ) {
                                CommonTextField(
                                    value = dueDate.toString(),
                                    readOnly= true,
                                    onValueChange = {},
                                    placeholder = dueDate.toString(),
                                    trailingIcon = {
                                        Icon(
                                            Icons.Rounded.CalendarMonth,
                                            contentDescription = ""
                                        )
                                    }
                                )
                            }
                        }
                        HorizontalSpace(12)
                        Column(modifier = Modifier.weight(1f)) {
                            Text("Due time")
                            VerticalSpace(8)
                            Surface(
                                onClick = {
                                    showTimePicker = true
                                },
                                shape = RoundedCornerShape(16.dp)
                            ) {
                                val time = "${dueTime.hour}:$taskMin"
                                CommonTextField(
                                    value = time,
                                    readOnly= true,
                                    onValueChange = {},
                                    placeholder = time,
                                    trailingIcon = {
                                        Icon(
                                            Icons.Rounded.AccessTime,
                                            contentDescription = ""
                                        )
                                    }
                                )
                            }
                        }
                    }
                    VerticalSpace(24)
                    Text("Priority")
                    VerticalSpace(8)
                    SelectTaskPriority(
                        initialPriority = priority,
                        onResponse = { result ->
                            priority = result
                        }
                    )
                    VerticalSpace(32)

                    VerticalSpace(32)
                    Button(
                        onClick = {
                            if(title.isEmpty()){
                                AppHelper.showToast(
                                    context,
                                    "Task title cannot be empty"
                                )
                            }else{
                                val taskToUpdate = taskByIdStateValue.data
                                if(taskToUpdate != null) {

                                    taskToUpdate.title = title
                                    taskToUpdate.description = description
                                    taskToUpdate.dueDate = dueDate
                                    taskToUpdate.dueDate = dueDate
                                    taskToUpdate.priority = priority

                                    taskViewmodel.updateTask(taskToUpdate)

                                    AppHelper.showToast(
                                        context, "Task updated successfully"
                                    )
                                }
                            }
                        },
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier.fillMaxWidth()

                    ) {
                        Text(
                            "Update task",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier.padding(10.dp)
                        )
                    }
                }
            }
        }

        if (showDatePicker) {
            ShowDatePickerDialog(
                onDismiss = {
                    showDatePicker = false
                },
                onDateSelected = { dateLong ->
                    dueDate = AppHelper.longToDate(dateLong)
                }
            )
        }

        if (showTimePicker) {
            ShowTimePickerDialog(
                onDismiss = {
                    showTimePicker = false
                },
                onConfirm = { timeState ->
                    dueTime = AppHelper.timeStateToLocalTime(
                        timeState.hour, timeState.minute
                    )
                }
            )
        }

        if(openAlertDialog.value){
            val taskToDelete = taskByIdStateValue.data
            if(taskToDelete != null){
                ShowAlertDialog(
                    onDismissRequest = {openAlertDialog.value = false},
                    onConfirmation = {
                       taskViewmodel.deleteTask(taskToDelete)

                        AppHelper.showToast(
                            context, "Task deleted successfully"
                        )

                        //navigate to home page
                        navController.navigate(Route.Home.name){
                            popUpTo(Route.Home.name) {
                                inclusive = true
                            }
                        }
                    },
                    title = "Delete Task",
                    body = "Are you sure you want to delete this task?",
                    icon = Icons.Rounded.Info,
                )
            }
        }
    }
}
