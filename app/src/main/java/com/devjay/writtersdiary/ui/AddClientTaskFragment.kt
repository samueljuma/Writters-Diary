package com.devjay.writtersdiary.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.devjay.writtersdiary.databinding.FragmentAddClientTaskBinding
import com.devjay.writtersdiary.viewmodels.AddTaskViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddClientTaskFragment : Fragment() {

    private lateinit var binding: FragmentAddClientTaskBinding

    private val viewModel: AddTaskViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAddClientTaskBinding.inflate(inflater,container,false)

        binding.viewModel =viewModel

        val args = AddClientTaskFragmentArgs.fromBundle(requireArguments())
        val clientId:Long = args.clientId
        viewModel.addTaskAndNavigateBackToClientTaskList.observe(viewLifecycleOwner,{
            if (it==true){
                addClientTaskToDatabase(clientId)
                this.findNavController().navigate(AddClientTaskFragmentDirections
                    .actionAddClientTaskFragmentToClientTaskListFragment(clientId))
                viewModel.doneNavigatingBackToClientTaskList()
            }
        })

        return binding.root
    }
    private fun addClientTaskToDatabase(clientId: Long) {
        val title = binding.taskTitleEditText.text.toString()
        val orderNo = binding.orderNumberEditText.text.toString()
        val wordCount = binding.wordCountEditText.text.toString().toInt()
        val amount = binding.amountPayableEditText.text.toString().toDouble()
        viewModel.addClientTask(clientId, title, orderNo, wordCount, amount)
        Toast.makeText(requireActivity(), "Task Added", Toast.LENGTH_SHORT).show()
    }

}