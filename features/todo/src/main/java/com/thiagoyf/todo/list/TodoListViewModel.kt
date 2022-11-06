package com.thiagoyf.todo.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thiagoyf.data.repository.TodoRepository
import com.thiagoyf.model.data.Todo
import com.thiagoyf.todo.list.model.TodoListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val todoRepository: TodoRepository
) : ViewModel() {

    val uiState: StateFlow<TodoListUiState> = todoRepository.getTodosStream()
        .map(TodoListUiState::Success)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = TodoListUiState.Loading
        )

    fun toggle(todo: Todo) {
        todoRepository.update(todo.copy(completed = !todo.completed))
    }
}