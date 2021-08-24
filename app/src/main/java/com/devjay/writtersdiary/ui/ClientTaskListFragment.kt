package com.devjay.writtersdiary.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.devjay.writtersdiary.R
import com.devjay.writtersdiary.adpters.ClientTaskListAdapter
import com.devjay.writtersdiary.adpters.ClientTaskListener
import com.devjay.writtersdiary.adpters.WriterTaskListAdapter
import com.devjay.writtersdiary.databinding.FragmentClientTaskListBinding
import com.devjay.writtersdiary.databinding.FragmentWriterTaskListBinding
import com.devjay.writtersdiary.viewmodels.ClientTaskListViewModel
import com.devjay.writtersdiary.viewmodels.ClientsListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ClientTaskListFragment : Fragment() {

    private lateinit var binding: FragmentClientTaskListBinding

    private val viewModel:ClientTaskListViewModel by viewModels()

    private val clientListViewModel: ClientsListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentClientTaskListBinding.inflate(inflater,container,false)

        val adapter = ClientTaskListAdapter(ClientTaskListener{
            clientTaskId ->  viewModel.onClientTaskUpdateClicked(clientTaskId)
        },viewModel)

        binding.clientTasksList.adapter =adapter
        binding.viewModel = viewModel

        val arguments = ClientTaskListFragmentArgs.fromBundle(requireArguments())
        val clientId = arguments.clientId

        subscribeUI(adapter,binding,clientId)

        /**
         * NAVIGATION OBSERVERS
         */
        viewModel.navigateToAddClientTask.observe(viewLifecycleOwner,{
            if(it==true){
                this.findNavController().navigate(ClientTaskListFragmentDirections
                    .actionClientTaskListFragmentToAddClientTaskFragment(clientId))
                viewModel.doneNavigatingToAddTasks()
            }
        })

        viewModel.navigateToUpdateClientTask.observe(viewLifecycleOwner, { clientTask->
            clientTask?.let {
                this.findNavController().navigate(ClientTaskListFragmentDirections
                    .actionClientTaskListFragmentToUpdateClientTaskFragment(clientTask, clientId))
                viewModel.doneNavigatingToUpdateClientTask()
            }
        })

        //get pending and completed Tasks
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

        /**
         * Delete Observer
         */
        viewModel.deleteClientTask.observe(viewLifecycleOwner, {
            it?.let {
                viewModel.deleteClientTask(it)
                if(it.isComplete){
                    completedTasks -=1
                    clientListViewModel.updateCompletedTasks(clientId,completedTasks)
                }else if (!it.isComplete){
                    pendingTasks -=1
                    clientListViewModel.updatePendingTasks(clientId,pendingTasks)
                }
            }
        })


        return binding.root
    }

    private fun subscribeUI(adapter: ClientTaskListAdapter, binding: FragmentClientTaskListBinding, clientId: Long){
        viewModel.getAllClientsTasks(clientId).observe(viewLifecycleOwner){ result ->
            binding.hasTasks = !result.isNullOrEmpty()
            adapter.submitList(result)
        }
    }

}