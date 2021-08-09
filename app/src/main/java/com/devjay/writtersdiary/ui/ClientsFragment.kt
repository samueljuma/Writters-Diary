package com.devjay.writtersdiary.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.devjay.writtersdiary.R
import com.devjay.writtersdiary.databinding.FragmentClientsBinding

/**
 * A simple [Fragment] subclass.
 * Use the [ClientsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ClientsFragment : Fragment() {

    private lateinit var binding: FragmentClientsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentClientsBinding.inflate(inflater,container,false)
        return binding.root
    }

}