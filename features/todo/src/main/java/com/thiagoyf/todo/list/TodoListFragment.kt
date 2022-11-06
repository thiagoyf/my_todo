package com.thiagoyf.todo.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.thiagoyf.route.extentions.navigateTo
import com.thiagoyf.route.todo.TodoRoutes
import com.thiagoyf.todo.databinding.FragmentTodoListBinding
import com.thiagoyf.todo.list.adapters.TodoListAdapter
import com.thiagoyf.todo.list.model.TodoListUiState
import com.thiagoyf.ui.SpaceItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TodoListFragment : Fragment() {

    private var _binding: FragmentTodoListBinding? = null
    private val binding get() = _binding!!

    private lateinit var todoListAdapter: TodoListAdapter

    private val todoListViewModel: TodoListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTodoListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpView()
        setUpObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpView() {
        setUpRecyclerView()
        setUpFab()
    }

    private fun setUpRecyclerView() {
        todoListAdapter = TodoListAdapter(
            editClickListener = { todo -> navigateTo(TodoRoutes.ADD, bundleOf("id" to todo.id)) },
            doneClickListener = { todo -> todoListViewModel.toggle(todo) }
        )
        binding.todoList.apply {
            adapter = todoListAdapter
            addItemDecoration(SpaceItemDecoration(resources.getDimensionPixelSize(com.thiagoyf.ui.R.dimen.spacing_8dp)))
        }
    }

    private fun setUpFab() {
        binding.addTodoFab.setOnClickListener {
            navigateTo(TodoRoutes.ADD)
        }
    }

    private fun setUpObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                todoListViewModel.uiState.collect {
                    when (it) {
                        is TodoListUiState.Success -> todoListAdapter.submitList(it.todos)
                        else -> Unit
                    }
                }
            }
        }
    }

}