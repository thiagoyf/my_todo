package com.thiagoyf.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.thiagoyf.database.dao.TodoDao
import com.thiagoyf.database.model.TodoEntity

@Database(
    entities = [
        TodoEntity::class
    ],
    version = 1
)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao
}