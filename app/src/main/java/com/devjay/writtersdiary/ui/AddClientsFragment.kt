package com.devjay.writtersdiary.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
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

            // Hide Keyboard
            val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view?.windowToken,0)

        }else{
            Toast.makeText(requireActivity(),"Enter Client's Name", Toast.LENGTH_SHORT).show()
        }
    }

}