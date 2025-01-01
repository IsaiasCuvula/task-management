package com.bersyte.taskmanagement.core.ui

data class UiState<T> (
    val data: T? = null,
    val error: String? = null,
    val isLoading: Boolean = false
)
