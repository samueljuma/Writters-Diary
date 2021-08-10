package com.devjay.writtersdiary.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.devjay.writtersdiary.databinding.FragmentAddClientsBinding
import com.devjay.writtersdiary.viewmodels.AddWritersOrClientsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddClientsFragment : Fragment() {

    private lateinit var binding : FragmentAddClientsBinding
    private val viewModel: AddWritersOrClientsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddClientsBinding.inflate(inflater,container,false)

        return binding.root
    }

}