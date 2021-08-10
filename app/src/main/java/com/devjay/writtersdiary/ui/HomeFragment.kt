package com.devjay.writtersdiary.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
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

        return binding.root
    }


}