package com.thiagoyf.data.di

import com.thiagoyf.data.repository.TodoRepository
import com.thiagoyf.data.repository.TodoRepositoryImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

object DataDI {
    val module = module {
        single<TodoRepository> {
            TodoRepositoryImpl(
                todoDao = get(),
                externalScope = get(named("applicationScope"))
            )
        }
    }
}