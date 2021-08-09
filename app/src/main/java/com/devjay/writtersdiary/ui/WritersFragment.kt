package com.devjay.writtersdiary.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.devjay.writtersdiary.R
import com.devjay.writtersdiary.databinding.FragmentWritersBinding


/**
 * A simple [Fragment] subclass.
 * Use the [WritersFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WritersFragment : Fragment() {

    private lateinit var binding: FragmentWritersBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWritersBinding.inflate(inflater,container,false)
        return binding.root
    }

}