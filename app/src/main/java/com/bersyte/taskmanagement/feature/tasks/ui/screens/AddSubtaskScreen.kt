package com.bersyte.taskmanagement.feature.tasks.ui.screens
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bersyte.taskmanagement.common.components.CommonTextField
import com.bersyte.taskmanagement.common.components.VerticalSpace
import com.bersyte.taskmanagement.feature.tasks.viewmodels.SubtaskViewmodel
import com.bersyte.taskmanagement.utils.AppHelper

@Composable
fun AddSubtaskScreen(
    taskId: Long,
    onClose: () -> Unit,
    subtaskViewmodel: SubtaskViewmodel = hiltViewModel()
) {
    var title by remember { mutableStateOf("")}
    val context = LocalContext.current

    val focusRequester = remember { FocusRequester()}

    //Request focus, as soon the this screen appears
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }


    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Add a Subtask",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )

                IconButton(onClick = onClose) {
                    Icon(
                        imageVector = Icons.Rounded.Close,
                        contentDescription = "Close add a subtask btn"
                    )
                }

            }
        }
        item {
            Column(
                modifier = Modifier.fillMaxWidth()
                    .padding(16.dp)
            ) {
                CommonTextField(
                    value = title,
                    onValueChange = {value -> title = value},
                    placeholder = title,
                    label = {
                        Text(
                            "Subtask",
                            style = MaterialTheme.typography.labelLarge.copy(
                                color = Color.Gray
                            )
                        )
                    },
                    modifier = Modifier.focusRequester(focusRequester)
                )
                VerticalSpace(24)
                Button(
                    modifier = Modifier.fillMaxWidth().height(50.dp),
                    shape = RoundedCornerShape(16.dp),
                    onClick = {
                        if(title.isEmpty()){
                          AppHelper.showToast(
                              context, "Please add subtask title"
                          )
                        }else{
                            subtaskViewmodel.saveSubtask(
                                taskOwnerId = taskId,
                                title = title,
                            )

                            //
                            AppHelper.showToast(
                                context, "Subtask saved successfully"
                            )
                            //
                            onClose()
                        }
                    }
                ) {
                    Text("Save")
                }
                VerticalSpace(24)
            }
        }
    }
}
