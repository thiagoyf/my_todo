package com.thiagoyf.database.di

import androidx.room.Room
import com.thiagoyf.database.TodoDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

object DatabaseDI {
    val module = module {
        single {
            Room.databaseBuilder(
                androidContext(),
                TodoDatabase::class.java,
                "todo-database"
            ).build()
        }
    }
}