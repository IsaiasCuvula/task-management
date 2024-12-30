package com.bersyte.taskmanagement.feature.tasks.views.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DatasetLinked
import androidx.compose.material.icons.rounded.AddLink
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.bersyte.taskmanagement.common.components.BottomSheet
import com.bersyte.taskmanagement.common.components.HorizontalSpace
import com.bersyte.taskmanagement.common.components.ThemedCard
import com.bersyte.taskmanagement.common.components.VerticalSpace
import com.bersyte.taskmanagement.common.components.WebViewScreen
import com.bersyte.taskmanagement.feature.tasks.views.screens.AddLinks
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FileAndLinks() {

    val filesLinks = remember {
        mutableStateListOf("billiffy.com", "google.com", "bersyte.com")
    }

    val selectedLink = remember {mutableStateOf("")}

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded  = true
    )
    val scope = rememberCoroutineScope()
    var showAddLinksSheet by remember { mutableStateOf(false) }
    var showWebViewSheet by remember { mutableStateOf(false) }

    ThemedCard(
        content = {
            filesLinks.addAll(emptyList())
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {showAddLinksSheet=true}
                ) {
                    Icon(
                        Icons.Rounded.AddLink,
                        contentDescription = "",
                        modifier = Modifier.size(30.dp),
                    )
                }
                Text("File & Links: ")
                HorizontalSpace(10)
                if(filesLinks.isEmpty()){
                    Text("No 🔗 links added yet...")
                }else{
                    LazyRow {
                        items(items = filesLinks, key = {it}){ item ->
                            Column(
                                modifier = Modifier
                                    .clip(shape = RoundedCornerShape(8.dp))
                                    .clickable {
                                        selectedLink.value = item
                                        showWebViewSheet = true
                                    }.padding(6.dp)
                            ) {
                                Icon(
                                    Icons.Filled.DatasetLinked,
                                    contentDescription = ""
                                )
                            }
                            HorizontalSpace(8)
                        }
                    }
                }
            }
            VerticalSpace(32)

            if(showAddLinksSheet){
                BottomSheet(
                    sheetState = sheetState,
                    onDismissRequest = {
                        showAddLinksSheet = false
                    },
                    content = {
                        AddLinks (
                            onClose = {
                                scope.launch { sheetState.hide() }
                                    .invokeOnCompletion {
                                        if(!sheetState.isVisible){
                                            showAddLinksSheet = false
                                        }
                                    }
                            }
                        )
                    }
                )
            }

            if(showWebViewSheet){
                BottomSheet(
                    sheetState = sheetState,
                    onDismissRequest = {
                        showWebViewSheet = false
                    },
                    content = {
                        WebViewScreen (
                            url = selectedLink.value,
                            onClose = {
                                scope.launch { sheetState.hide() }
                                    .invokeOnCompletion {
                                        if(!sheetState.isVisible){
                                            showWebViewSheet = false
                                        }
                                    }
                            }
                        )
                    }
                )
            }
        }
    )
}
