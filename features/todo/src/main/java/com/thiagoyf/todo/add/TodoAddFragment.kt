package com.thiagoyf.todo.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.thiagoyf.route.extentions.popBackStack
import com.thiagoyf.todo.R
import com.thiagoyf.todo.databinding.FragmentTodoAddBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TodoAddFragment : Fragment() {

    private var _binding: FragmentTodoAddBinding? = null
    private val binding get() = _binding!!

    private val todoId: String? by lazy { arguments?.getString("id") }

    private val todoAddViewModel: TodoAddViewModel by viewModels()

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