package com.bersyte.taskmanagement.feature.profile.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.bersyte.taskmanagement.feature.profile.viewmodels.ProfileViewModel


@Composable
fun HomeUsername(profileViewModel: ProfileViewModel = hiltViewModel()
) {

    val profileState = profileViewModel.profileState.collectAsState()
    val profileStateValue = profileState.value

    when {
        profileStateValue.data != null -> {
            val user = profileStateValue.data

            Text(
                user.username,
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}
