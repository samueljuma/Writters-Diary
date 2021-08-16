package com.devjay.writtersdiary.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.asFlow
import com.devjay.writtersdiary.R
import com.devjay.writtersdiary.data.entities.WriterTask
import com.devjay.writtersdiary.databinding.FragmentUpdateWriterTaskBinding
import com.devjay.writtersdiary.viewmodels.UpdateTaskViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateWriterTaskFragment : Fragment() {

    private lateinit var binding: FragmentUpdateWriterTaskBinding

    private val vieModel: UpdateTaskViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentUpdateWriterTaskBinding.inflate(inflater,container, false)

        val args = UpdateWriterTaskFragmentArgs.fromBundle(requireArguments())
        val writerTaskId = args.writerTaskId

        binding.viewModel = vieModel

        val writerTask = vieModel.getWriterTask(writerTaskId)

        writerTask.observe(viewLifecycleOwner, {
            it?.let {
                binding.writerTask =it
            }
        })

        return binding.root
    }

}