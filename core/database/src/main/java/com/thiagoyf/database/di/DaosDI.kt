package com.thiagoyf.database.di

import com.thiagoyf.database.TodoDatabase
import org.koin.dsl.module

object DaosDI {
    val module = module {
        single {
            get<TodoDatabase>().todoDao()
        }
    }
}