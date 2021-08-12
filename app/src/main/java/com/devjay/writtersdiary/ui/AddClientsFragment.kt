package com.devjay.writtersdiary.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.devjay.writtersdiary.data.entities.Client
import com.devjay.writtersdiary.data.entities.Writer
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

        binding.viewModel = viewModel

        viewModel.navigateBackToClientsList.observe(viewLifecycleOwner, Observer {
            if(it==true){
                val name = binding.clientNameEditText.text.toString()
                addClientAndNavigateBack(name,viewModel)
            }
        })

        return binding.root
    }

    private fun addClientAndNavigateBack(name: String, viewModel: AddWritersOrClientsViewModel){
        if(name.isNotBlank()){
            val client = Client(name = name)
            viewModel.addClient(client)
            Toast.makeText(requireActivity(),"Client Added Successfully", Toast.LENGTH_SHORT).show()
            this.findNavController().navigate(AddClientsFragmentDirections
                .actionAddClientsFragmentToClientsFragment())
            viewModel.doneNavigatingBackToClientsList()
        }else{
            Toast.makeText(requireActivity(),"Enter Client's Name", Toast.LENGTH_SHORT).show()
        }
    }

}