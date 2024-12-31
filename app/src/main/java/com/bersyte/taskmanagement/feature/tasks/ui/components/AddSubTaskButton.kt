package com.bersyte.taskmanagement.feature.tasks.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bersyte.taskmanagement.common.components.VerticalSpace
import com.bersyte.taskmanagement.feature.tasks.ui.screens.AddSubTask
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun  AddSubTaskButton() {

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded  = true
    )
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }


    Column {
        OutlinedButton(
            modifier = Modifier.fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(16.dp),
            onClick = {showBottomSheet = true}
        ) {
            Text(
                "Add a subtask",
                color = Color.White,
                style =   MaterialTheme.typography.titleMedium.copy(
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
