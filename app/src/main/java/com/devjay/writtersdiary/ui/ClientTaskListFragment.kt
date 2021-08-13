package com.devjay.writtersdiary.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.devjay.writtersdiary.R
import com.devjay.writtersdiary.databinding.FragmentClientTaskListBinding
import com.devjay.writtersdiary.viewmodels.ClientTaskListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ClientTaskListFragment : Fragment() {

    private lateinit var binding: FragmentClientTaskListBinding

    private val viewModel:ClientTaskListViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentClientTaskListBinding.inflate(inflater,container,false)

        return binding.root
    }

}