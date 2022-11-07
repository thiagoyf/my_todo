package com.thiagoyf.data.repository

import com.thiagoyf.database.dao.TodoDao
import com.thiagoyf.database.model.TodoEntity
import com.thiagoyf.model.data.Todo
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test

class TodoRepositoryImplTest {

    private lateinit var todoRepositoryImpl: TodoRepositoryImpl

    @MockK
    private lateinit var todoDao: TodoDao

    private lateinit var testScope: TestScope

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        testScope = TestScope()
        todoRepositoryImpl = TodoRepositoryImpl(
            todoDao,
            testScope
        )
    }

    @Test
    fun `should call getTodosEntitiesStream when getTodosStream is called`() = runTest {
        // Given
        every { todoDao.getTodosEntitiesStream() } returns flowOf(
            listOf(
                TodoEntity(id = "idDummy1", title = "titleDummy1", completed = true),
                TodoEntity(id = "idDummy2", title = "titleDummy2", completed = false),
            )
        )

        // When
        val result = todoRepositoryImpl.getTodosStream().first()

        // Then
        verify(exactly = 1) {
            todoDao.getTodosEntitiesStream()
        }
        assertThat(result.size, equalTo(2))

        assertThat(result[0], instanceOf(Todo::class.java))
        assertThat(result[0].id, equalTo("idDummy1"))
        assertThat(result[0].title, equalTo("titleDummy1"))
        assertThat(result[0].completed, equalTo(true))

        assertThat(result[0], instanceOf(Todo::class.java))
        assertThat(result[1].id, equalTo("idDummy2"))
        assertThat(result[1].title, equalTo("titleDummy2"))
        assertThat(result[1].completed, equalTo(false))
    }

    @Test
    fun `should call getTodoEntity when getTodoById is called`() = runTest {
        // Given
        val id = "idDummy"
        val expected = TodoEntity(id = id, title = "titleDummy", completed = false)
        coEvery { todoDao.getTodoEntity(any()) } returns expected

        // When
        val result = todoRepositoryImpl.getTodoById(id)

        // Then
        coVerify (exactly = 1) {
            todoDao.getTodoEntity(id)
        }

        assertThat(result, instanceOf(Todo::class.java))
        assertThat(result.id, equalTo(expected.id))
        assertThat(result.title, equalTo(expected.title))
        assertThat(result.completed, equalTo(expected.completed))
    }

    @Test
    fun `should call insert when add is called`() = testScope.runTest {
        // Given
        val todo = Todo(id = "idDummy", title = "titleDummy", completed = false)
        val todoEntity = TodoEntity(id = "idDummy", title = "titleDummy", completed = false)
        coJustRun { todoDao.insert(any()) }

        // When
        todoRepositoryImpl.add(todo)
        advanceUntilIdle()

        // Then
        coVerify (exactly = 1) {
            todoDao.insert(todoEntity)
        }
    }

    @Test
    fun `should call update when update is called`() = testScope.runTest {
        // Given
        val todo = Todo(id = "idDummy", title = "titleDummy", completed = false)
        val todoEntity = TodoEntity(id = "idDummy", title = "titleDummy", completed = false)
        coJustRun { todoDao.insert(any()) }

        // When
        todoRepositoryImpl.update(todo)
        advanceUntilIdle()

        // Then
        coVerify (exactly = 1) {
            todoDao.update(todoEntity)
        }
    }

}