package com.thiagoyf.data.repository

import com.thiagoyf.model.data.Todo
import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    fun getTodosStream(): Flow<List<Todo>>
    suspend fun getTodoById(id: String): Todo
    fun add(todo: Todo)
    fun update(todo: Todo)
}