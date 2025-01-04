package com.bersyte.taskmanagement.common.components

import android.Manifest
import android.app.Application
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Mic
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.StopCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.bersyte.taskmanagement.feature.voice.VoiceToTextParser

@Composable
fun SearchField(
    query: String,
    onQueryChanged: (String) -> Unit,
    onQueryClear: ()-> Unit,
    modifier: Modifier = Modifier,
    onSpeaking: (String) -> Unit
) {

    val context = LocalContext.current
    val app = context.applicationContext as Application

    val voiceToTextParser by lazy {
        VoiceToTextParser(app)
    }

    var canRecord by remember { mutableStateOf(false) }
    val recordAudioLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        canRecord = it
    }

    LaunchedEffect(key1= recordAudioLauncher) {
        recordAudioLauncher.launch(Manifest.permission.RECORD_AUDIO)
    }

    val state by voiceToTextParser.state.collectAsState()

    val focusManager = LocalFocusManager.current

    Column {
        AnimatedContent(
            targetState = state.isSpeaking,
            label = ""
        ) { isSpeaking  ->
            if(isSpeaking){
                Text("🔊 Speaking...")
            }else{
                onSpeaking(state.spokenText)
            }
        }

        CommonTextField(
            modifier = modifier,
            value = query,
            onValueChange = onQueryChanged,
            placeholder = "Search task",
            leadingIcon = {
                IconButton(
                    onClick = {
                        if(state.isSpeaking){
                            voiceToTextParser.stopListening()
                        }else{
                            voiceToTextParser.startListening()
                        }
                    }
                ) {
                    AnimatedContent(
                        targetState = state.isSpeaking,
                        label = ""
                    ) { isSpeaking ->
                        if (isSpeaking){
                            Icon(
                                Icons.Rounded.StopCircle,
                                contentDescription = "Stop",
                            )
                        }else{
                            Icon(
                                Icons.Rounded.Mic,
                                contentDescription = "Voice",
                            )
                        }
                    }
                }
            },
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
}
