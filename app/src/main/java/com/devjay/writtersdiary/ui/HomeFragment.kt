package com.devjay.writtersdiary.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.devjay.writtersdiary.R
import com.devjay.writtersdiary.databinding.FragmentHomeBinding
import com.devjay.writtersdiary.viewmodels.HomeViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)

        setHasOptionsMenu(true)

        binding.lifecycleOwner =this

        //let the fragment know about the viewModel
        binding.homeViewModel = viewModel
        /**
         * NAVIGATION OBSERVERS
         */
        viewModel.navigateToWriterListFragment.observe(viewLifecycleOwner, Observer {
             if (it==true){
                 this.findNavController().navigate(HomeFragmentDirections
                     .actionHomeFragmentToWritersFragment())
                 viewModel.doneNavigatingToWritersFragment()
             }

        })
        viewModel.navigateToClientListFragment.observe(viewLifecycleOwner, Observer {
            if(it==true){
                this.findNavController().navigate(HomeFragmentDirections
                    .actionHomeFragmentToClientsFragment())
                viewModel.doneNavigatingToClientsFragment()
            }
        })

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item,
            requireView().findNavController())
                ||super.onOptionsItemSelected(item)
    }
}