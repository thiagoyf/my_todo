package com.thiagoyf.database.model

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class TodoEntityTest {

    @Test
    fun `should return a Todo when asTodo is called`() {
        // Given
        val todoEntity = TodoEntity(
            id = "idDummy",
            title = "titleDummy",
            completed = true
        )

        // When
        val result = todoEntity.asTodo()

        // Then
        assertThat(result.id, equalTo(todoEntity.id))
        assertThat(result.title, equalTo(todoEntity.title))
        assertThat(result.completed, equalTo(todoEntity.completed))
    }

}