package com.bersyte.taskmanagement.feature.home.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.bersyte.taskmanagement.common.components.CommonTextField

@Composable
fun SearchField(
    query: String,
    onQueryChanged: (String) -> Unit,
    onQueryClear: ()-> Unit,
    modifier: Modifier = Modifier,
) {

    val focusManager = LocalFocusManager.current

    CommonTextField(
        modifier = modifier,
        value = query,
        onValueChange = onQueryChanged,
        placeholder = "Search task",
        trailingIcon = {
            if(query.isEmpty()){
                Icon(
                    Icons.Rounded.Search,
                    contentDescription = "Search icon",
                    modifier = Modifier.size(32.dp)
                )
            }else{
                IconButton(
                    onClick = onQueryClear
                ) {
                    Icon(
                        Icons.Rounded.Close,
                        contentDescription = "Clear search query",
                        modifier = Modifier.size(26.dp)
                    )
                }
            }
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                focusManager.clearFocus()
            }
        ),
    )
}
