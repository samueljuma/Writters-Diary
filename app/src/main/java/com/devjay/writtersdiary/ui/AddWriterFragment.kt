package com.devjay.writtersdiary.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.devjay.writtersdiary.data.entities.Writer
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

        binding.viewModel = viewModel

        /**
         * NAVIGATION OBSERVERS
         */
        viewModel.navigateBackToWritersList.observe(viewLifecycleOwner, Observer {
            if(it==true){
                val name = binding.writersNameEditText.text.toString()
                addWriterAndNavigateBack(name,viewModel)
            }
        })
        return binding.root
    }

    //Add Writer and Navigate Back
    private fun addWriterAndNavigateBack(name: String, viewModel: AddWritersOrClientsViewModel){
        if(name.isNotBlank()){
            val writer = Writer(name = name)
            viewModel.addWriter(writer)
            Toast.makeText(requireActivity(),"Writer Added Successfully",Toast.LENGTH_SHORT).show()
            this.findNavController().navigate(AddWriterFragmentDirections.actionAddWriterFragmentToWritersFragment())
            viewModel.doneNavigatingBackToWritersList()

            // Hide Keyboard
            val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view?.windowToken,0)
        }else{
            Toast.makeText(requireActivity(),"Enter Writer's Name",Toast.LENGTH_SHORT).show()
        }
    }

}