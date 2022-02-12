package com.example.feature_details.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.feature_details.databinding.FragmentDetailsBinding
import com.example.feature_details.viewmodel.DetailsViewModel

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<DetailsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentDetailsBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initObservers()
    }

    private fun initObservers() {

    }

    private fun initViews() = with(binding){
        val id = arguments?.getInt("todoId")!!
        val title = arguments?.getString("todoTitle")
        val content = arguments?.getString("todoContent")
        val complete = arguments?.getBoolean("todoComplete")!!
        tvTitle.text = title
        tvContent.text = content
        cbCompleted.isChecked = complete

        cbCompleted.setOnClickListener{
            viewModel.checkCompleted(id, cbCompleted.isChecked)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}