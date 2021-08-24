package com.devjay.writtersdiary.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.devjay.writtersdiary.adpters.WriterTaskListAdapter
import com.devjay.writtersdiary.adpters.WriterTaskListener
import com.devjay.writtersdiary.databinding.FragmentWriterTaskListBinding
import com.devjay.writtersdiary.viewmodels.WriterTaskListViewModel
import com.devjay.writtersdiary.viewmodels.WritersListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WriterTaskListFragment : Fragment() {

    private lateinit var binding: FragmentWriterTaskListBinding

    private val viewModel: WriterTaskListViewModel by viewModels()

    private val writersListViewModel: WritersListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWriterTaskListBinding.inflate(inflater, container, false)

        val adapter = WriterTaskListAdapter(WriterTaskListener {
            writerTaskId ->  viewModel.onWriterTaskUpdateClicked(writerTaskId)
        },viewModel)

        binding.viewModel = viewModel

        val arguments = WriterTaskListFragmentArgs.fromBundle(requireArguments())
        val writerId = arguments.writerID

        binding.writerTasksList.adapter = adapter


        subscribeUI(adapter,binding,writerId)

        /**
         * NAVIGATION OBSERVERS
         */
        viewModel.navigateToAddWriterTask.observe(viewLifecycleOwner, {
            if(it ==true){
                this.findNavController().navigate(WriterTaskListFragmentDirections
                    .actionWriterTaskListFragmentToAddWriterTaskFragment(writerId))
                viewModel.doneNavigatingToAddTasks()
            }
        })
        viewModel.navigateToUpdateWriterTask.observe(viewLifecycleOwner, { writerTask ->
            writerTask?.let {
                this.findNavController().navigate(WriterTaskListFragmentDirections
                    .actionWriterTaskListFragmentToUpdateWriterTaskFragment(writerTask, writerId))
                viewModel.doneNavigatingToUpdateWriterTask()
            }
        })
        /**
         * Get complete and pending tasks
         */
        var pendingTasks=0
        var completedTasks=0

        writersListViewModel.getAllPendingTasks(writerId).observe(viewLifecycleOwner,{ allPendingTasks->
            allPendingTasks?.let {
                pendingTasks = allPendingTasks.size
            }
        })
        writersListViewModel.getAllCompletedTasks(writerId).observe(viewLifecycleOwner,{ allCompleteTasks->
            allCompleteTasks?.let {
                completedTasks = allCompleteTasks.size
            }
        })

        /**
         *  delete observer
          */

        viewModel.deleteWriterTask.observe(viewLifecycleOwner, {
            it?.let {
                viewModel.deleteWriterTask(it)
                if(it.isComplete){
                    completedTasks -=1
                    writersListViewModel.updateCompletedTasks(writerId,completedTasks)
                }
                if(!it.isComplete){
                    pendingTasks -=1
                    writersListViewModel.updatePendingTasks(writerId,pendingTasks)
                }
                Toast.makeText(requireActivity(), "${it.title} order: ${it.orderNumber} deleted",Toast.LENGTH_SHORT).show()
            }
        })

        return binding.root
    }

    private fun subscribeUI(adapter: WriterTaskListAdapter, binding: FragmentWriterTaskListBinding, writerId: Long){
        viewModel.getAllWriterTasks(writerId).observe(viewLifecycleOwner){ result ->
            binding.hasTasks = !result.isNullOrEmpty()
            adapter.submitList(result)
        }
    }

}