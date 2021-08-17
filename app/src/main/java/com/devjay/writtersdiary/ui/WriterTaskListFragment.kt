package com.devjay.writtersdiary.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.devjay.writtersdiary.adpters.WriterTaskListAdapter
import com.devjay.writtersdiary.adpters.WriterTaskListener
import com.devjay.writtersdiary.databinding.FragmentWriterTaskListBinding
import com.devjay.writtersdiary.viewmodels.WriterTaskListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WriterTaskListFragment : Fragment() {

    private lateinit var binding: FragmentWriterTaskListBinding

    private val viewModel: WriterTaskListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWriterTaskListBinding.inflate(inflater, container, false)

        binding.viewModel = viewModel
        val adapter = WriterTaskListAdapter(WriterTaskListener {
            writerTaskId ->  viewModel.onWriterTaskUpdateClicked(writerTaskId)
        })

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

        return binding.root
    }

    private fun subscribeUI(adapter: WriterTaskListAdapter, binding: FragmentWriterTaskListBinding, writerId: Long){
        viewModel.getAllWriterTasks(writerId).observe(viewLifecycleOwner){ result ->
            binding.hasTasks = !result.isNullOrEmpty()
            adapter.submitList(result)
        }
    }

}