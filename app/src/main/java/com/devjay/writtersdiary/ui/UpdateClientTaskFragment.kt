package com.devjay.writtersdiary.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.devjay.writtersdiary.data.entities.ClientTask
import com.devjay.writtersdiary.data.entities.WriterTask
import com.devjay.writtersdiary.databinding.FragmentUpdateClientTaskBinding
import com.devjay.writtersdiary.viewmodels.ClientsListViewModel
import com.devjay.writtersdiary.viewmodels.UpdateClientTaskViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateClientTaskFragment : Fragment() {

    private lateinit var binding: FragmentUpdateClientTaskBinding

    private val viewModel: UpdateClientTaskViewModel by viewModels()

    private val clientListViewModel: ClientsListViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentUpdateClientTaskBinding.inflate(inflater, container,false)

        val args = UpdateClientTaskFragmentArgs.fromBundle(requireArguments())
        val clientTaskId = args.clientTaskId
        val clientId = args.clientId

        binding.viewModel = viewModel

        val clientTask = viewModel.getClientTask(clientTaskId)

        clientTask.observe(viewLifecycleOwner, {
            it?.let {
                binding.clientTask = it
            }
        })

        var pendingTasks =0
        var completedTasks =0

        clientListViewModel.getAllPendingTasks(clientId).observe(viewLifecycleOwner,{ allPendingTasks->
            allPendingTasks?.let {
                pendingTasks = allPendingTasks.size
            }
        })
        clientListViewModel.getAllCompletedTasks(clientId).observe(viewLifecycleOwner,{ allCompleteTasks->
            allCompleteTasks?.let {
                completedTasks = allCompleteTasks.size
            }
        })

        viewModel.updateTaskAndNavigateBackToClientTaskList.observe(viewLifecycleOwner, {
            it?.let{ clientTask->
                updateClientTaskAndGoBack(clientTask,pendingTasks,completedTasks,clientTaskId,clientId)

                // Hide Keyboard
                val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view?.windowToken,0)
            }
        })

        viewModel.cancelUpdatingTaskAndNavigateBackToClientTaskList.observe(viewLifecycleOwner,{
            if(it==true){
                this.findNavController().navigate(UpdateClientTaskFragmentDirections
                    .actionUpdateClientTaskFragmentToClientTaskListFragment(clientId))
                viewModel.doneCancelingAndNavigatingBackToClientTaskList()

                // Hide Keyboard
                val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view?.windowToken,0)

            }
        })


        return binding.root
    }

    private fun updateClientTaskAndGoBack(clientTask: ClientTask, pendingTasks: Int,
                                          completedTasks: Int, clientTaskId: Long, clientId: Long) {
        var pendingTasks1 = pendingTasks
        var completedTasks1 = completedTasks
        val title = binding.titleEditText.text.toString()
        val orderNo = binding.orderNumberEditText.text.toString()
        val wordCount = binding.wordCountEditText.text.toString().toInt()
        val amountPayable = binding.amtPayableEditText.text.toString().toDouble()
        val isComplete = binding.isCompleteCheckbox.isChecked
        val isPaid = binding.isPaidCheckbox.isChecked
        if (clientTask.isComplete && !isComplete) {
            pendingTasks1 += 1
            completedTasks1 -= 1

        } else if (!clientTask.isComplete && isComplete) {
            pendingTasks1 -= 1
            completedTasks1 += 1
        }
        viewModel.updateClientTask(title, orderNo, wordCount, amountPayable, isComplete, isPaid, clientTaskId)
        this.findNavController().navigate(UpdateClientTaskFragmentDirections
            .actionUpdateClientTaskFragmentToClientTaskListFragment(clientId))
        viewModel.doneNavigatingBackToClientTaskList()

        // update pending and completed Tasks for the specific client
        clientListViewModel.updateCompletedTasks(clientId, completedTasks1)
        clientListViewModel.updatePendingTasks(clientId, pendingTasks1)
    }


}