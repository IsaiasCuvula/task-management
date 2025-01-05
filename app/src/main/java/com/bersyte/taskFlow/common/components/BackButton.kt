package com.bersyte.taskFlow.common.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController

@Composable
fun BackButton(navController: NavHostController) {

    IconButton(
        onClick = {
            if( navController
                .currentBackStackEntry?.lifecycle?.currentState
                == Lifecycle.State.RESUMED
            ){
                navController.popBackStack()
            }
        }
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Back button"
        )
    }
}
