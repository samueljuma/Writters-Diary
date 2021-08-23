package com.devjay.writtersdiary.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.devjay.writtersdiary.databinding.FragmentUpdateWriterTaskBinding
import com.devjay.writtersdiary.viewmodels.UpdateTaskViewModel
import com.devjay.writtersdiary.viewmodels.UpdateWriterTaskViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateWriterTaskFragment : Fragment() {

    private lateinit var binding: FragmentUpdateWriterTaskBinding

    private val vieModel: UpdateWriterTaskViewModel by viewModels()

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

        vieModel.updateTaskAndNavigateBackToWritersTaskList.observe(viewLifecycleOwner,{
            if(it==true){
                updateAndGoBack(writerTaskId, writerId)
            }
        })
        vieModel.cancelUpdatingTaskAndNavigateBackToWritersTaskList.observe(viewLifecycleOwner,{
            if(it==true){
                this.findNavController().navigate(UpdateWriterTaskFragmentDirections
                    .actionUpdateWriterTaskFragmentToWriterTaskListFragment(writerId))
                vieModel.doneCancelingAndNavigatingBackToWriterTaskList()
            }
        })


        return binding.root
    }

    private fun updateAndGoBack(writerTaskId: Long, writerId: Long) {
        val title = binding.titleEditText.text.toString()
        val orderNo = binding.orderNumberEditText.text.toString()
        val wordCount = binding.wordCountEditText.text.toString().toInt()
        val amountPayable = binding.amtPayableEditText.text.toString().toDouble()
        val isComplete = binding.isCompleteCheckbox.isChecked
        val isPaid = binding.isPaidCheckbox.isChecked
        vieModel.updateWriterTask(title, orderNo, wordCount, amountPayable, isComplete, isPaid, writerTaskId)
        this.findNavController().navigate(
            UpdateWriterTaskFragmentDirections
                .actionUpdateWriterTaskFragmentToWriterTaskListFragment(writerId)
        )
        vieModel.doneNavigatingBackToWriterTaskList()
    }

}