package com.devjay.writtersdiary.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.devjay.writtersdiary.databinding.FragmentAddWriterBinding
import com.devjay.writtersdiary.viewmodels.AddWritersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddWriterFragment : Fragment() {

    private lateinit var binding: FragmentAddWriterBinding
    private val viewModel: AddWritersViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAddWriterBinding.inflate(inflater,container,false)

        return binding.root
    }
}