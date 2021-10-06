package com.devjay.writtersdiary.ui

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.devjay.writtersdiary.R
import com.devjay.writtersdiary.adpters.ClientListener
import com.devjay.writtersdiary.adpters.ClientsListAdapter
import com.devjay.writtersdiary.databinding.FragmentClientsBinding
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

        setHasOptionsMenu(true)

        val adapter = ClientsListAdapter(ClientListener {
                clientId ->  viewModel.onViewClientTasksClicked(clientId)
        },viewModel)
        binding.clientList.adapter=adapter

        subscribeUI(adapter,binding)

        binding.viewModel = viewModel

        /**
         * NAVIGATION OBSERVERS
         */
        viewModel.navigateToAddClientFragment.observe(viewLifecycleOwner, Observer {
            if(it==true){
                this.findNavController().navigate(ClientsFragmentDirections
                    .actionClientsFragmentToAddClientsFragment())
                viewModel.doneNavigatingToAddClientsFragment()
            }
        })

        viewModel.navigateToClientTasks.observe(viewLifecycleOwner, Observer { client->
            client?.let {
                this.findNavController().navigate(ClientsFragmentDirections
                    .actionClientsFragmentToClientTaskListFragment(client))
                viewModel.doneNavigatingToClientTasks()
            }

        })

        /**
         * delete observer
         */
        viewModel.deleteClient.observe(viewLifecycleOwner, {
            it?.let {
                viewModel.deleteClient(it)
            }
        })

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.writers_overflow_menu, menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.deleteAllWriters -> {
                viewModel.deleteAllClients()
                Toast.makeText(context,"All Clients Deleted", Toast.LENGTH_SHORT).show()
                true
            }
            else ->{
                return NavigationUI.onNavDestinationSelected(item,
                    requireView().findNavController())
                        ||super.onOptionsItemSelected(item)
            }
        }

    }

    private fun subscribeUI(adapter: ClientsListAdapter, binding: FragmentClientsBinding){
        viewModel.listOfClients.observe(viewLifecycleOwner){ result ->
            binding.hasClients = !result.isNullOrEmpty()
            adapter.submitList(result)
        }
    }

}