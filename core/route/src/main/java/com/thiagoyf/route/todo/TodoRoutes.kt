package com.thiagoyf.route.todo

import com.thiagoyf.route.R
import com.thiagoyf.route.Route

enum class TodoRoutes(override val action: Int): Route {
    ADD(R.id.action_addTodo)
}