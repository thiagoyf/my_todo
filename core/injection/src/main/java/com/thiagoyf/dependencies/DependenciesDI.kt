package com.thiagoyf.dependencies

import com.thiagoyf.data.di.DataDI
import com.thiagoyf.database.di.DaosDI
import com.thiagoyf.database.di.DatabaseDI
import com.thiagoyf.todo.di.TodoDI

object DependenciesDI {
    val modules = DataDI.module +
            DaosDI.module +
            DatabaseDI.module +
            TodoDI.module
}