package com.bersyte.taskmanagement.feature.tasks.ui.screens

import android.Manifest
import android.app.Application
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Mic
import androidx.compose.material.icons.rounded.StopCircle
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.bersyte.taskmanagement.common.components.BackButton
import com.bersyte.taskmanagement.common.components.NoTasksMessageCard
import com.bersyte.taskmanagement.common.components.LoadingIndicator
import com.bersyte.taskmanagement.common.components.ShowErrorMessage
import com.bersyte.taskmanagement.feature.tasks.ui.components.TaskCard
import com.bersyte.taskmanagement.common.components.VerticalSpace
import com.bersyte.taskmanagement.common.components.SearchField
import com.bersyte.taskmanagement.common.components.SearchTaskResult
import com.bersyte.taskmanagement.feature.tasks.viewmodels.TaskViewmodel
import com.bersyte.taskmanagement.feature.voice.VoiceToTextParser

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TasksScreen(
    navController: NavHostController,
    taskViewmodel: TaskViewmodel = hiltViewModel(),
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

    val taskState = taskViewmodel.taskState.collectAsState()
    val taskStateValue = taskState.value
    var query by remember { mutableStateOf("") }


    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                ),
                navigationIcon = {
                    BackButton(navController)
                },
                title = {
                    Text("All tasks")
                }
            )
        }
    ){ innerPadding ->

        Column(
            modifier =  Modifier.padding(innerPadding)
        ) {
            LazyColumn {
                item {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        AnimatedContent(
                            targetState = state.isSpeaking,
                            label = ""
                        ) { isSpeaking  ->
                            if(isSpeaking){
                                Text("🔊 Speaking...")
                            }else{
                                query = state.spokenText
                            }
                        }
                        SearchField(
                            query = query,
                            onQueryChanged = {query = it},
                            onQueryClear = {query = ""},
                            leadingIcon = {
                                IconButton(
                                    onClick = {
                                        if (state.isSpeaking) {
                                            voiceToTextParser.stopListening()
                                        } else {
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
                        )
                        VerticalSpace(32)
                        if(query.isNotEmpty()){
                            taskStateValue.data?.let {
                                SearchTaskResult(
                                    query= query,
                                    allTask = it,
                                    navController = navController
                                )
                            }
                            VerticalSpace(32)
                        }else{
                            when{
                                taskStateValue.isLoading -> {
                                    LoadingIndicator()
                                }

                                taskStateValue.error != null -> {
                                    ShowErrorMessage(taskStateValue.error)
                                }

                                taskStateValue.data != null -> {
                                    val tasks = taskStateValue.data

                                    if(tasks.isEmpty()){
                                        VerticalSpace(64)
                                        NoTasksMessageCard(
                                            navController = navController,
                                            msg = "You're all caught up! No tasks for now"
                                        )
                                    }else{
                                        tasks.forEachIndexed { index, task ->
                                            TaskCard(navController, task = task)
                                            if(index != tasks.lastIndex){
                                                VerticalSpace(8)
                                                HorizontalDivider()
                                                VerticalSpace(8)
                                            }
                                        }
                                    }
                                }
                            }
                            VerticalSpace(24)
                        }
                    }
                }
            }
        }
    }
}
