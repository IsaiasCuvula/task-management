package com.bersyte.taskmanagement.feature.home.views.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SearchField(
    query: String,
    onQueryChanged: (String) -> Unit,
    onQueryClear: ()-> Unit,
    modifier: Modifier = Modifier,
) {
    TextField(
        value = query,
        onValueChange = onQueryChanged,
        placeholder = { Text("Search task") },
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
        modifier = modifier.fillMaxWidth()
            .clip(shape = RoundedCornerShape((16.dp))),
        colors = TextFieldDefaults.colors(
            disabledIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = MaterialTheme.colorScheme.onSurface,
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            disabledContainerColor = MaterialTheme.colorScheme.surface
        )
    )
}
