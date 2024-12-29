package com.bersyte.taskmanagement.feature.tasks.views.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bersyte.taskmanagement.common.components.BackButton
import com.bersyte.taskmanagement.common.components.CommonTextField
import com.bersyte.taskmanagement.common.components.HorizontalSpace
import com.bersyte.taskmanagement.common.components.VerticalSpace
import com.bersyte.taskmanagement.feature.tasks.views.components.ChooseTaskImportance
import com.bersyte.taskmanagement.feature.tasks.views.components.ShowDatePickerDialog
import com.bersyte.taskmanagement.utils.AppHelper

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen(navController: NavHostController) {

    var title by remember {  mutableStateOf("") }
    var description by remember {  mutableStateOf("") }
    var dueDate by remember {  mutableStateOf("") }
    var dueTime by remember {  mutableStateOf("") }

    var selectedDate by remember { mutableStateOf<Long?>(null) }
    var showDatePicker by remember { mutableStateOf(false) }

    val today = AppHelper.getCurrentDate()

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
            modifier = Modifier.fillMaxSize()
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
                         onValueChange = { newValue ->
                             title = newValue
                         },
                         placeholder = "Enter task title"
                     )
                     VerticalSpace(16)
                     Text("Description")
                     VerticalSpace(8)
                     CommonTextField(
                         modifier = Modifier.fillMaxWidth()
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
                                     value = dueDate,
                                     readOnly= true,
                                     onValueChange = { newValue ->
                                         dueDate = newValue
                                     },
                                     placeholder = today.date.toString(),
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
                                     showDatePicker = true
                                 },
                                shape = RoundedCornerShape(16.dp)
                             ) {
                                 CommonTextField(
                                     value = dueTime,
                                     readOnly= true,
                                     onValueChange = { newValue ->
                                         dueTime = newValue
                                     },
                                     placeholder = today.time.toString(),
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
                     ChooseTaskImportance()
                     VerticalSpace(32)
                     Button(
                         onClick = {},
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

        if (showDatePicker) {
            ShowDatePickerDialog(
                onDismiss = {
                    showDatePicker = false
                },
                onDateSelected = {}
            )
        }
    }
}
