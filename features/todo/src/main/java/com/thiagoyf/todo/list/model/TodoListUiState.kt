package com.thiagoyf.todo.list.model

import com.thiagoyf.model.data.Todo

sealed interface TodoListUiState {
    data class Success(val todos: List<Todo>) : TodoListUiState
    object Error : TodoListUiState
    object Loading : TodoListUiState
}