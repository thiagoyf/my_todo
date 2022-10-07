package com.thiagoyf.todo.list.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.thiagoyf.model.data.Todo
import com.thiagoyf.todo.databinding.ItemTodoBinding

typealias TodoClickListener = (todo: Todo) -> Unit

class TodoListAdapter(
    private val editClickListener: TodoClickListener,
    private val doneClickListener: TodoClickListener
) : ListAdapter<Todo, TodoListAdapter.TodoViewHolder>(TodoItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            ItemTodoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class TodoViewHolder(private val binding: ItemTodoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(todo: Todo) {
            binding.root.setOnClickListener {
                editClickListener.invoke(todo)
            }

            binding.title.text = todo.title
            binding.check.isChecked = todo.completed

            binding.check.setOnClickListener {
                doneClickListener.invoke(todo)
            }
        }
    }
}

private class TodoItemCallback : DiffUtil.ItemCallback<Todo>() {
    override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
        return oldItem == newItem
    }
}