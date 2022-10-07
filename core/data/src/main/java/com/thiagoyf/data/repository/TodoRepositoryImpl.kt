package com.thiagoyf.data.repository

import com.thiagoyf.data.model.asEntity
import com.thiagoyf.database.dao.TodoDao
import com.thiagoyf.database.model.TodoEntity
import com.thiagoyf.database.model.asTodo
import com.thiagoyf.model.data.Todo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class TodoRepositoryImpl(
    private val todoDao: TodoDao,
    private val externalScope: CoroutineScope
): TodoRepository {
    override fun getTodosStream(): Flow<List<Todo>> = todoDao.getTodosEntitiesStream()
            .map { it.map(TodoEntity::asTodo) }

    override suspend fun getTodoById(id: String): Todo {
        return todoDao.getTodoEntity(id).asTodo()
    }

    override fun add(todo: Todo) {
        externalScope.launch {
            todoDao.insert(todo.asEntity())
        }
    }

    override fun update(todo: Todo) {
        externalScope.launch {
            todoDao.update(TodoEntity(todo.id, todo.title, todo.completed))
        }
    }
}