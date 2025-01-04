package com.bersyte.taskmanagement.feature.about.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bersyte.taskmanagement.common.components.BackButton
import com.bersyte.taskmanagement.common.components.VerticalSpace

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(navController: NavHostController) {

    val textStyle = MaterialTheme.typography

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("About TaskFlow") },
                navigationIcon = { BackButton(navController) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                ),
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize()
                   .padding(innerPadding)
                   .padding(horizontal =  16.dp)
        ) {
            item{

                VerticalSpace(16)
                Text(
                    text = "Welcome to TaskFlow, your ultimate task management app designed to help you stay organized, productive, and in control of your day-to-day responsibilities. Whether you need to manage personal errands, professional projects, or collaborative goals, TaskFlow provides a seamless and intuitive experience.",
                    textAlign = TextAlign.Justify
                )

                VerticalSpace(16)

                Text(
                    text = "Features at a Glance",
                    style = textStyle.titleLarge.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                VerticalSpace(8)

                Text(
                    text = "- Task Management Made Simple\nEffortlessly create tasks with just a few taps. Assign priorities, set deadlines, and track progress all in one place.",
                    textAlign = TextAlign.Justify
                )
                VerticalSpace(8)

                Text(
                    text = "- Subtask Support\nBreak your tasks into smaller, manageable subtasks for better focus and execution. Complete tasks step by step to achieve your goals faster.",
                    textAlign = TextAlign.Justify
                )
                VerticalSpace(8)

                Text(
                    text = "- Intuitive UI with Android Compose\nExperience a sleek, modern interface built with Jetpack Compose. TaskFlow ensures a fast, responsive, and visually appealing user experience.",
                    textAlign = TextAlign.Justify
                )
                VerticalSpace(8)

                Text(
                    text = "- Reminders and Notifications\nNever miss a deadline with customizable reminders and smart notifications that keep you on track.",
                    textAlign = TextAlign.Justify
                )
                VerticalSpace(8)

                Text(
                    text = "- Seamless Organization\nCategorize tasks using tags or labels, and sort them by priority, due date, or project.",
                    textAlign = TextAlign.Justify
                )
                VerticalSpace(8)

                Text(
                    text = "- Offline Access\nWork on your tasks anytime, even without an internet connection. TaskFlow get your back.",
                    textAlign = TextAlign.Justify
                )

                VerticalSpace(16)

                Text(
                    text = "Why Choose TaskFlow?",
                    style = textStyle.titleLarge.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                VerticalSpace(8)

                Text(
                    text = "At TaskFlow, we believe that managing your tasks should be effortless and stress-free. With a focus on user-centric design and advanced functionality, our app is tailored to help you achieve more with less effort. Whether you’re organizing your personal life or collaborating with a team, TaskFlow adapts to your needs.",
                    textAlign = TextAlign.Justify
                )
                VerticalSpace(16)
                Text(
                    text = "Get Started Today",
                    style = textStyle.titleLarge.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                VerticalSpace(8)

                Text(
                    text = "Download TaskFlow and take the first step toward better productivity. Create tasks, organize subtasks, and watch your to-do list transform into actionable accomplishments.\n\nTaskFlow: Your productivity, reimagined.",
                    textAlign = TextAlign.Justify
                )
                VerticalSpace(32)
            }
        }
    }
}
