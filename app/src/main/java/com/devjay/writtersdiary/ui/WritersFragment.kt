package com.devjay.writtersdiary.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.devjay.writtersdiary.adpters.WritersListAdapter
import com.devjay.writtersdiary.databinding.FragmentWritersBinding
import com.devjay.writtersdiary.viewmodels.WritersListViewModel
import dagger.hilt.android.AndroidEntryPoint


/**
 * A simple [Fragment] subclass.
 * Use the [WritersFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class WritersFragment : Fragment() {

    private lateinit var binding: FragmentWritersBinding

    private val viewModel: WritersListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWritersBinding.inflate(inflater,container,false)

        val adapter = WritersListAdapter()
        binding.writersList.adapter =adapter

        // handle recyclerview
        subscribeUI(adapter,binding)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        /**
         * NAVIGATION OBSERVERS
         */
        viewModel.navigateToAddWriterFragment.observe(viewLifecycleOwner, Observer {
            if(it==true){
                this.findNavController().navigate(WritersFragmentDirections.actionWritersFragmentToAddWriterFragment())
                viewModel.doneNavigatingToAddWritersFragment()
            }
        })
        return binding.root
    }

    private fun subscribeUI(adapter: WritersListAdapter, binding: FragmentWritersBinding){
        viewModel.listOfWriters.observe(viewLifecycleOwner){ result ->
            binding.hasWriters = !result.isNullOrEmpty()
            adapter.submitList(result)
        }
    }

}