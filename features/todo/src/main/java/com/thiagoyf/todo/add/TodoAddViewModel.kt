package com.thiagoyf.todo.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thiagoyf.data.repository.TodoRepository
import com.thiagoyf.model.data.Todo
import com.thiagoyf.todo.add.model.TodoObservable
import kotlinx.coroutines.launch
import java.util.*

class TodoAddViewModel(
    private val todoRepository: TodoRepository
) : ViewModel() {

    val todo: TodoObservable = TodoObservable()

    fun loadTodo(id: String) {
        viewModelScope.launch {
            val loadedTodo = todoRepository.getTodoById(id)
            todo.id = loadedTodo.id
            todo.title = loadedTodo.title
            todo.completed = loadedTodo.completed
        }
    }

    fun add() {
        val todo = Todo(
            id = UUID.randomUUID().toString(),
            title = todo.title,
            completed = todo.completed
        )
        todoRepository.add(todo)
    }

    fun update() {
        val todo = Todo(
            id = todo.id,
            title = todo.title,
            completed = todo.completed
        )
        todoRepository.update(todo)
    }

}