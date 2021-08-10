package com.devjay.writtersdiary.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.devjay.writtersdiary.databinding.FragmentAddWriterBinding
import com.devjay.writtersdiary.viewmodels.AddWritersOrClientsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddWriterFragment : Fragment() {

    private lateinit var binding: FragmentAddWriterBinding
    private val viewModel: AddWritersOrClientsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAddWriterBinding.inflate(inflater,container,false)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.addWriterBtn.setOnClickListener{
            Toast.makeText(requireActivity(),"Writer Added Successfully",Toast.LENGTH_SHORT).show()
        }
        return binding.root
    }
}