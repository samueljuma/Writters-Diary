package com.devjay.writtersdiary.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.devjay.writtersdiary.R
import com.devjay.writtersdiary.adpters.ClientsListAdapter
import com.devjay.writtersdiary.adpters.WritersListAdapter
import com.devjay.writtersdiary.databinding.FragmentClientsBinding
import com.devjay.writtersdiary.databinding.FragmentWritersBinding
import com.devjay.writtersdiary.viewmodels.ClientsListViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass.
 * Use the [ClientsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class ClientsFragment : Fragment() {

    private lateinit var binding: FragmentClientsBinding
    private val viewModel:ClientsListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentClientsBinding.inflate(inflater,container,false)

        val adapter = ClientsListAdapter()
        binding.clientList.adapter=adapter

        subscribeUI(adapter,binding)

        binding.viewModel = viewModel

        viewModel.navigateToAddClientFragment.observe(viewLifecycleOwner, Observer {
            if(it==true){
                this.findNavController().navigate(ClientsFragmentDirections
                    .actionClientsFragmentToAddClientsFragment())
                viewModel.doneNavigatingToAddClientsFragment()
            }
        })

        return binding.root
    }

    private fun subscribeUI(adapter: ClientsListAdapter, binding: FragmentClientsBinding){
        viewModel.listOfClients.observe(viewLifecycleOwner){ result ->
            binding.hasClients = !result.isNullOrEmpty()
            adapter.submitList(result)
        }
    }

}