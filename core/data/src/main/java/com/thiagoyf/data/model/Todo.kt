package com.thiagoyf.data.model

import com.thiagoyf.database.model.TodoEntity
import com.thiagoyf.model.data.Todo

fun Todo.asEntity() = TodoEntity(
    id = id,
    title = title,
    completed = completed
)