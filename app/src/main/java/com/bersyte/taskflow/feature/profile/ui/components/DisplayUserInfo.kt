package com.bersyte.taskflow.feature.profile.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bersyte.taskflow.feature.profile.viewmodels.ProfileViewModel

@Composable
fun DisplayUserInfo(
    profileViewModel: ProfileViewModel = hiltViewModel()
) {

    val profileState = profileViewModel.profileState.collectAsState()
    val profileStateValue = profileState.value

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.primary
            ).height(160.dp),
        contentAlignment = Alignment.TopEnd
    ) {
        when {
            profileStateValue.data != null -> {
                val user = profileStateValue.data

                EditProfileButton(user)
                Column(
                    modifier = Modifier.fillMaxSize().padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Hi",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        user.username,
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            }
        }
    }
}
