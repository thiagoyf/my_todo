package com.thiagoyf.todo.add.model

sealed interface TodoAddUiState {
    object Idle : TodoAddUiState
    object Loading : TodoAddUiState
    object Success : TodoAddUiState
    data class Error(val throwable: Throwable) : TodoAddUiState
}