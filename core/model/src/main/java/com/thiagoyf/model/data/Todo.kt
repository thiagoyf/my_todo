package com.thiagoyf.model.data

import java.util.UUID

data class Todo(
    val id: String = UUID.randomUUID().toString(),
    val title: String = "",
    val completed: Boolean = false
)