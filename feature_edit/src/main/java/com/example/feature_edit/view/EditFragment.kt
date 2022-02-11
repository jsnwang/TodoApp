package com.example.feature_edit.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.feature_edit.databinding.FragmentEditBinding
import com.example.feature_edit.viewmodel.EditViewModel
import kotlinx.coroutines.launch

class EditFragment : Fragment() {

    private var _binding: FragmentEditBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<EditViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) = FragmentEditBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initObservers()
    }

    private fun initObservers() {
        confirmDiscard()
        confirmDelete()
        enableOrDisableSaveButton()
    }

    private fun initViews() = with(binding) {
        val todoId = arguments?.getInt("todoId")!!
        lifecycleScope.launch {
            val todo = viewModel.getTodo(todoId)
            editTitle.setText(todo.title)
            editContent.setText(todo.content)
        }
    }

    private fun confirmDelete() = with(binding) {
        this?.deleteButton?.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Confirm delete")
                .setMessage("Are you sure you want to delete?")
                .setPositiveButton("Confirm") { _, _ ->
                    // navigate back to ListFragment with a slide down animation
                    findNavController().navigate(com.example.todo.R.id.todoGraph)
                }
                .setNegativeButton("Cancel") { _, _ ->

                }
                .show()
        }
    }

    private fun confirmDiscard() = with(binding) {
        this?.discardButton?.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Confirm discard")
                .setMessage("Are you sure you want to discard your changes?")
                .setPositiveButton("Confirm") { _, _ ->
                    // navigate back to ListFragment with a slide down animation
                    findNavController().navigate(com.example.todo.R.id.todoGraph)
                }
                .setNegativeButton("Cancel") { _, _ ->

                }
                .show()
        }
    }

    private fun enableOrDisableSaveButton() = with(binding) {
        this?.editTitle?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //   TODO("Not yet implemented")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // TODO("Not yet implemented")
            }

            override fun afterTextChanged(s: Editable?) {
                if (s != null) {
                    if (s.isNotEmpty()) this@with?.fabSave?.isEnabled = true
                }
                if (s != null) {
                    if (s.isEmpty()) this@with?.fabSave?.isEnabled = false
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}