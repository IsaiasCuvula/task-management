package com.bersyte.taskmanagement.feature.tasks.views.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bersyte.taskmanagement.common.components.BackButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen(navController: NavHostController) {

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
          Column(
              modifier = Modifier.fillMaxSize()
                  .padding(innerPadding)
                  .padding(horizontal = 16.dp),
          ){}
      }
}
