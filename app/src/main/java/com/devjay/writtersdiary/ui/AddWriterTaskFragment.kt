package com.devjay.writtersdiary.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.devjay.writtersdiary.databinding.FragmentAddWriterTaskBinding
import com.devjay.writtersdiary.viewmodels.AddTaskViewModel
import com.devjay.writtersdiary.viewmodels.WritersListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddWriterTaskFragment : Fragment() {

    private lateinit var binding: FragmentAddWriterTaskBinding

    private val viewModel: AddTaskViewModel by viewModels()

    private val writersListViewModel: WritersListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddWriterTaskBinding.inflate(inflater,container,false)

        binding.viewModel =viewModel

        val args = AddWriterTaskFragmentArgs.fromBundle(requireArguments())
        val writerId:Long = args.writerId

        //get pending Tasks
        var pendingTasks =0
        writersListViewModel.getAllPendingTasks(writerId).observe(viewLifecycleOwner,{
            it?.let {
                pendingTasks = it.size
            }
        })

        viewModel.addTaskAndNavigateBackToWriterTaskList.observe(viewLifecycleOwner,{
            if (it==true){
                addWriterTaskToDatabase(writerId)
                //update pending Tasks
                writersListViewModel.updatePendingTasks(writerId,pendingTasks+1)

                //navigate back to WriterTask List
                this.findNavController().navigate(AddWriterTaskFragmentDirections
                    .actionAddWriterTaskFragmentToWriterTaskListFragment(writerId))
                viewModel.doneNavigatingBackToWriterTaskList()
            }
        })


        return binding.root
    }

    private fun addWriterTaskToDatabase(writerId: Long) {
        val title = binding.taskTitleEditText.text.toString()
        val orderNo = binding.orderNumberEditText.text.toString()
        val wordCount = binding.wordCountEditText.text.toString().toInt()
        val amount = binding.amountPayableEditText.text.toString().toDouble()
        viewModel.addWriterTask(writerId, title, orderNo, wordCount, amount)
        Toast.makeText(requireActivity(), "Task Added", Toast.LENGTH_SHORT).show()
    }

}