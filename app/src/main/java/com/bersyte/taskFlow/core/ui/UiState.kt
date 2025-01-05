package com.bersyte.taskFlow.core.ui

data class UiState<T> (
    val data: T? = null,
    val error: String? = null,
    val isLoading: Boolean = false
)
