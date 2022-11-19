package com.thiagoyf.data.model

import com.thiagoyf.model.data.Todo
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class TodoTest {

    @Test
    fun `should return a TodoEntity when asEntity is called`() {
        // Given
        val todo = Todo(
            id = "idDummy",
            title = "titleDummy",
            completed = true
        )

        // When
        val result = todo.asEntity()

        // Then
        assertThat(result.id, equalTo(todo.id))
        assertThat(result.title, equalTo(todo.title))
        assertThat(result.completed, equalTo(todo.completed))
    }

}