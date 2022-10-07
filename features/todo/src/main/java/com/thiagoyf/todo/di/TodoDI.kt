package com.thiagoyf.todo.di

import com.thiagoyf.todo.add.TodoAddViewModel
import com.thiagoyf.todo.list.TodoListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object TodoDI {
    val module = module {
        viewModel { TodoListViewModel(todoRepository = get()) }

        viewModel { TodoAddViewModel(todoRepository = get()) }
    }
}