package com.thiagoyf.database.dao

import androidx.room.*
import com.thiagoyf.database.model.TodoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Query(value = "SELECT * FROM todos")
    fun getTodosEntitiesStream(): Flow<List<TodoEntity>>

    @Query(
        value = """
        SELECT * FROM todos
        WHERE id = :id
    """
    )
    suspend fun getTodoEntity(id: String): TodoEntity

    @Update
    suspend fun update(todo: TodoEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(todo: TodoEntity): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOrIgnoreTodos(todos: List<TodoEntity>): List<Long>
}