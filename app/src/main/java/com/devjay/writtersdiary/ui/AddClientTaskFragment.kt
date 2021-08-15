package com.devjay.writtersdiary.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.devjay.writtersdiary.databinding.FragmentAddClientTaskBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddClientTaskFragment : Fragment() {

    private lateinit var binding: FragmentAddClientTaskBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAddClientTaskBinding.inflate(inflater,container,false)

        return binding.root
    }

}