package com.thiagoyf.data.di

import com.thiagoyf.data.repository.TodoRepository
import com.thiagoyf.data.repository.TodoRepositoryImpl
import com.thiagoyf.database.dao.TodoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineScope

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideTodoRepository(
        todoDao: TodoDao,
        externalScope: CoroutineScope
    ): TodoRepository {
        return TodoRepositoryImpl(
            todoDao,
            externalScope
        )
    }

}