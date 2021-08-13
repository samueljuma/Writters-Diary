package com.devjay.writtersdiary.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.devjay.writtersdiary.databinding.FragmentWriterTaskListBinding
import com.devjay.writtersdiary.viewmodels.WriterTaskListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WriterTaskListFragment : Fragment() {

    private lateinit var binding: FragmentWriterTaskListBinding

    private val viewModel: WriterTaskListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWriterTaskListBinding.inflate(inflater, container, false)

        return binding.root
    }

}