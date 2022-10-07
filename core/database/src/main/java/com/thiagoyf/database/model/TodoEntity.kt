package com.thiagoyf.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.thiagoyf.model.data.Todo
import java.util.*

@Entity(tableName = "todos")
data class TodoEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val completed: Boolean
)

fun TodoEntity.asTodo() = Todo(
    id = id,
    title = title,
    completed = completed
)