package com.devjay.writtersdiary.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.devjay.writtersdiary.databinding.FragmentAddClientTaskBinding
import com.devjay.writtersdiary.viewmodels.AddTaskViewModel
import com.devjay.writtersdiary.viewmodels.ClientsListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddClientTaskFragment : Fragment() {

    private lateinit var binding: FragmentAddClientTaskBinding

    private val viewModel: AddTaskViewModel by viewModels()

    private val clientListViewModel: ClientsListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAddClientTaskBinding.inflate(inflater,container,false)

        binding.viewModel =viewModel

        val args = AddClientTaskFragmentArgs.fromBundle(requireArguments())
        val clientId:Long = args.clientId

        //get pending Tasks
        var pendingTasks =0
        clientListViewModel.getAllPendingTasks(clientId).observe(viewLifecycleOwner,{
            it?.let {
                pendingTasks = it.size
            }
        })
        viewModel.addTaskAndNavigateBackToClientTaskList.observe(viewLifecycleOwner,{
            if (it==true){
                addClientTaskToDatabase(clientId)

                // update pending Tasks
                clientListViewModel.updatePendingTasks(clientId, pendingTasks+1)

                // navigate back to ClientTask List
                this.findNavController().navigate(AddClientTaskFragmentDirections
                    .actionAddClientTaskFragmentToClientTaskListFragment(clientId))
                viewModel.doneNavigatingBackToClientTaskList()

                // Hide Keyboard
                val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view?.windowToken,0)
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