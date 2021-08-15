package com.devjay.writtersdiary.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.devjay.writtersdiary.databinding.FragmentAddWriterTaskBinding
import com.devjay.writtersdiary.viewmodels.AddTaskViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddWriterTaskFragment : Fragment() {

    private lateinit var binding: FragmentAddWriterTaskBinding

    private val viewModel: AddTaskViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddWriterTaskBinding.inflate(inflater,container,false)

        return binding.root
    }

}