package com.bersyte.taskFlow.feature.tasks.ui.screens

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
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
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
import com.bersyte.taskFlow.common.components.BackButton
import com.bersyte.taskFlow.common.components.CommonTextField
import com.bersyte.taskFlow.common.components.HorizontalSpace
import com.bersyte.taskFlow.common.components.LoadingIndicator
import com.bersyte.taskFlow.common.components.VerticalSpace
import com.bersyte.taskFlow.common.navigation.Route
import com.bersyte.taskFlow.feature.tasks.data.models.Task
import com.bersyte.taskFlow.feature.tasks.data.models.TaskPriority
import com.bersyte.taskFlow.feature.tasks.ui.components.SelectTaskPriority
import com.bersyte.taskFlow.feature.tasks.ui.components.ShowDatePickerDialog
import com.bersyte.taskFlow.feature.tasks.ui.components.ShowTimePickerDialog
import com.bersyte.taskFlow.feature.tasks.viewmodels.TaskViewmodel
import com.bersyte.taskFlow.utils.AppHelper

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen(
    navController: NavHostController,
    viewmodel: TaskViewmodel = hiltViewModel()
) {
    val today = AppHelper.getCurrentDate()
    var title by remember {  mutableStateOf("") }
    var description by remember {  mutableStateOf("") }
    var dueDate by remember {  mutableStateOf(today.date) }
    var dueTime by remember {  mutableStateOf(today.time) }
    var priority by remember {  mutableStateOf(TaskPriority.LOW) }

    var showTimePicker by remember { mutableStateOf(false) }
    var showDatePicker by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    val taskState = viewmodel.taskState.collectAsState()
    val isLoading = taskState.value.isLoading

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Add task") },
                navigationIcon = { BackButton(navController) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                ),
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
                         placeholder = "Enter task description",
                         keyboardOptions = KeyboardOptions(
                             imeAction = ImeAction.Done
                         ),
                         keyboardActions = KeyboardActions(
                             onNext = {
                                 focusManager.clearFocus()
                             }
                         ),
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
                                 val time = "${dueTime.hour}:${dueTime.minute}"
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
                         onResponse = { result ->
                             priority = result
                         }
                     )
                     VerticalSpace(32)

                     if (isLoading){
                         LoadingIndicator()
                     }else{
                         Button(
                             onClick = {
                                 if(title.isEmpty()){
                                     AppHelper.showToast(
                                         context,
                                         "Task title cannot be empty"
                                     )
                                 }else{
                                     val newTask = Task(
                                         taskId = 0,
                                         title = title,
                                         description = description,
                                         dueDate = dueDate,
                                         dueTime = dueTime,
                                         priority = priority,
                                     )
                                     viewmodel.saveTask(newTask)

                                     //navigate to home page
                                     navController.navigate(Route.Home.name){
                                         popUpTo(Route.Home.name) {
                                             inclusive = true
                                         }
                                     }

                                 }
                             },
                             shape = RoundedCornerShape(16.dp),
                             modifier = Modifier.fillMaxWidth()

                         ) {
                             Text(
                                 "Save task",
                                 style = MaterialTheme.typography.titleMedium.copy(
                                     fontWeight = FontWeight.Bold
                                 ),
                                 modifier = Modifier.padding(10.dp)
                             )
                         }
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
    }
}
