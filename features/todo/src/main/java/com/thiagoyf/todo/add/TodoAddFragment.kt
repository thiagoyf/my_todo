package com.thiagoyf.todo.add

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.thiagoyf.model.data.Todo
import com.thiagoyf.route.extentions.popBackStack
import com.thiagoyf.todo.R
import com.thiagoyf.todo.add.model.TodoAddUiState
import com.thiagoyf.todo.databinding.FragmentTodoAddBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class TodoAddFragment : Fragment() {

    private var _binding: FragmentTodoAddBinding? = null
    private val binding get() = _binding!!

    private val todoId: String? by lazy { arguments?.getString("id") }

    private val todoAddViewModel: TodoAddViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTodoAddBinding.inflate(inflater, container, false)
        binding.todoAddViewModel = todoAddViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpViews()
        val id = todoId
        if (id != null) {
            todoAddViewModel.loadTodo(id)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpViews() {
        setUpBack()
        setUpAddButton()
    }

    private fun setUpBack() {
        binding.back.setOnClickListener {
            popBackStack()
        }
    }

    private fun setUpAddButton() {
        val id = todoId
        binding.addTodoBtn.text =
            if (id != null) getString(R.string.edit)
            else getString(R.string.add)

        binding.addTodoBtn.setOnClickListener {
            if (id != null) todoAddViewModel.update()
            else todoAddViewModel.add()
            popBackStack()
        }
    }

}