package com.thiagoyf.todo.add.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.thiagoyf.model.data.Todo
import com.thiagoyf.todo.BR

class TodoObservable : BaseObservable() {

    @get:Bindable
    var id: String = ""
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.id)
            }
        }

    @get:Bindable
    var title: String = ""
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.title)
            }
        }

    @get:Bindable
    var completed: Boolean = false
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.completed)
            }
        }

}

fun Todo.asTodoObservable() : TodoObservable {
    val todoObservable = TodoObservable()
    todoObservable.id = this.id
    todoObservable.title = this.title
    todoObservable.completed = this.completed
    return todoObservable
}