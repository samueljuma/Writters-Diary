package com.devjay.writtersdiary.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.devjay.writtersdiary.databinding.FragmentUpdateClientTaskBinding
import com.devjay.writtersdiary.viewmodels.UpdateClientTaskViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateClientTaskFragment : Fragment() {

    private lateinit var binding: FragmentUpdateClientTaskBinding

    private val viewModel: UpdateClientTaskViewModel by viewModels()
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

        viewModel.updateTaskAndNavigateBackToClientTaskList.observe(viewLifecycleOwner, {
            it?.let{
                updateAndGoBack(clientTaskId,clientId)
            }
        })

        viewModel.cancelUpdatingTaskAndNavigateBackToClientTaskList.observe(viewLifecycleOwner,{
            if(it==true){
                this.findNavController().navigate(UpdateClientTaskFragmentDirections
                    .actionUpdateClientTaskFragmentToClientTaskListFragment(clientId))
                viewModel.doneCancelingAndNavigatingBackToClientTaskList()
            }
        })


        return binding.root
    }

    private fun updateAndGoBack(clientTaskId: Long, clientId: Long) {
        val title = binding.titleEditText.text.toString()
        val orderNo = binding.orderNumberEditText.text.toString()
        val wordCount = binding.wordCountEditText.text.toString().toInt()
        val amountPayable = binding.amtPayableEditText.text.toString().toDouble()
        val isComplete = binding.isCompleteCheckbox.isChecked
        val isPaid = binding.isPaidCheckbox.isChecked
        viewModel.updateClientTask(title, orderNo, wordCount, amountPayable, isComplete, isPaid, clientTaskId)
        this.findNavController().navigate(UpdateClientTaskFragmentDirections
            .actionUpdateClientTaskFragmentToClientTaskListFragment(clientId))
        viewModel.doneNavigatingBackToClientTaskList()
    }

}