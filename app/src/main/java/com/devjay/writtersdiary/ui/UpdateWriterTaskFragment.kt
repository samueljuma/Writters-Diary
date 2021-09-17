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
import com.devjay.writtersdiary.data.entities.WriterTask
import com.devjay.writtersdiary.databinding.FragmentUpdateWriterTaskBinding
import com.devjay.writtersdiary.viewmodels.UpdateWriterTaskViewModel
import com.devjay.writtersdiary.viewmodels.WritersListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateWriterTaskFragment : Fragment() {

    private lateinit var binding: FragmentUpdateWriterTaskBinding

    private val vieModel: UpdateWriterTaskViewModel by viewModels()

    private val writersListViewModel: WritersListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentUpdateWriterTaskBinding.inflate(inflater,container, false)

        val args = UpdateWriterTaskFragmentArgs.fromBundle(requireArguments())
        val writerTaskId = args.writerTaskId
        val writerId = args.writerId

        binding.viewModel = vieModel

        val writerTask = vieModel.getWriterTask(writerTaskId)

        writerTask.observe(viewLifecycleOwner, {
            it?.let {
                binding.writerTask =it
            }
        })

        var pendingTasks =0
        var completedTasks =0

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

        vieModel.updateTaskAndNavigateBackToWritersTaskList.observe(viewLifecycleOwner,{
            it?.let{ writerTask->
                updateWriterTaskAndGoBack(writerTask, pendingTasks, completedTasks, writerTaskId, writerId)

                // Hide Keyboard
                val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view?.windowToken,0)
            }
        })
        vieModel.cancelUpdatingTaskAndNavigateBackToWritersTaskList.observe(viewLifecycleOwner,{
            if(it==true){
                this.findNavController().navigate(UpdateWriterTaskFragmentDirections
                    .actionUpdateWriterTaskFragmentToWriterTaskListFragment(writerId))
                vieModel.doneCancelingAndNavigatingBackToWriterTaskList()

                // Hide Keyboard
                val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view?.windowToken,0)
            }
        })


        return binding.root
    }

    private fun updateWriterTaskAndGoBack(writerTask: WriterTask, pendingTasks: Int,
                                 completedTasks: Int, writerTaskId: Long, writerId: Long) {
        var pendingTasks1 = pendingTasks
        var completedTasks1 = completedTasks
        val title = binding.titleEditText.text.toString()
        val orderNo = binding.orderNumberEditText.text.toString()
        val wordCount = binding.wordCountEditText.text.toString().toInt()
        val amountPayable = binding.amtPayableEditText.text.toString().toDouble()
        val isComplete = binding.isCompleteCheckbox.isChecked
        val isPaid = binding.isPaidCheckbox.isChecked
        if (writerTask.isComplete && !isComplete) {
            pendingTasks1 += 1
            completedTasks1 -= 1

        } else if (!writerTask.isComplete && isComplete) {
            pendingTasks1 -= 1
            completedTasks1 += 1
        }
        vieModel.updateWriterTask(title, orderNo, wordCount, amountPayable, isComplete, isPaid, writerTaskId)
        this.findNavController().navigate(UpdateWriterTaskFragmentDirections
                .actionUpdateWriterTaskFragmentToWriterTaskListFragment(writerId))
        vieModel.doneNavigatingBackToWriterTaskList()

        // update pending and completed Tasks for the specific writer
        writersListViewModel.updateCompletedTasks(writerId, completedTasks1)
        writersListViewModel.updatePendingTasks(writerId, pendingTasks1)
    }


}