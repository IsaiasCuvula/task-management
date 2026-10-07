package com.bersyte.taskflow.common.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(
    content: @Composable ()-> Unit,
    sheetState: SheetState,
    onDismissRequest: () -> Unit
) {

    ModalBottomSheet(
        sheetState = sheetState,
        modifier = Modifier.fillMaxWidth()
            .heightIn(
                max = LocalConfiguration.current.screenHeightDp.dp * 0.9f
            ),
        onDismissRequest = onDismissRequest,
        dragHandle = {}
    ) {
        content()
    }
}
