package com.bersyte.taskmanagement.feature.tasks.views.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ContentPaste
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bersyte.taskmanagement.common.components.CommonTextField
import com.bersyte.taskmanagement.common.components.VerticalSpace

@Composable
fun AddLinks(onClose: () -> Unit) {
    var taskLink by remember { mutableStateOf("") }

    val clipboardManager = LocalClipboardManager.current

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
                    "Add Link",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )

                IconButton(onClick = onClose) {
                    Icon(
                        imageVector = Icons.Rounded.Close,
                        contentDescription = "Close add link btn"
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
                    value = taskLink,
                    onValueChange = {value -> taskLink = value},
                    placeholder = taskLink,
                    label = {
                        Text(
                            "🔗 link ...",
                            style = MaterialTheme.typography.labelLarge.copy(
                                color = Color.Gray
                            )
                        )
                    },
                    trailingIcon = {
                        if(taskLink.isNotEmpty()){
                            IconButton(
                                onClick = {taskLink = ""}
                            ) {
                                Icon(
                                    Icons.Filled.Close,
                                    contentDescription = "Clear"
                                )
                            }
                        }
                    },
                    leadingIcon = {
                        IconButton(
                            onClick = {
                               val data = clipboardManager.getText()
                                taskLink = (data?.text ?: "").toString()
                            }
                        ) {
                            Icon(
                                Icons.Filled.ContentPaste,
                                contentDescription = "Paste"
                            )
                        }
                    }
                )
                VerticalSpace(24)
                Button(
                    modifier = Modifier.fillMaxWidth().height(50.dp),
                    shape = RoundedCornerShape(16.dp),
                    onClick = {
                        //Close bottom sheet
                        onClose()
                    }
                ) {
                    Text("Save")
                }
                VerticalSpace(24)
            }
        }
    }
}
