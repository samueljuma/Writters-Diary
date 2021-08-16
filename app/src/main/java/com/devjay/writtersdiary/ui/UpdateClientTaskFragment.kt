package com.devjay.writtersdiary.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.devjay.writtersdiary.databinding.FragmentUpdateClientTaskBinding
import com.devjay.writtersdiary.viewmodels.UpdateTaskViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateClientTaskFragment : Fragment() {

    private lateinit var binding: FragmentUpdateClientTaskBinding

    private val viewModel: UpdateTaskViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentUpdateClientTaskBinding.inflate(inflater, container,false)

        val args = UpdateClientTaskFragmentArgs.fromBundle(requireArguments())
        val clientTaskId = args.clientTaskId

        val clientTask = viewModel.getClientTask(clientTaskId)

        clientTask.observe(viewLifecycleOwner, {
            it?.let {
                binding.clientTask = it
            }
        })

        return binding.root
    }

}