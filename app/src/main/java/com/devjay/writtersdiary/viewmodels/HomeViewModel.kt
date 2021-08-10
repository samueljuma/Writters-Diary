package com.devjay.writtersdiary.viewmodels

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.devjay.writtersdiary.data.repository.ClientRepository
import com.devjay.writtersdiary.data.repository.WriterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val clientRepository: ClientRepository,
    private val writerRepository: WriterRepository
): ViewModel() {

    val clients = clientRepository.getAllClients().asLiveData()
    val writers = writerRepository.getAllWriters().asLiveData()

    /**
     * NAVIGATION
     */
    // navigation to writersList Fragment
    private val _navigateToWriterListFragment = MutableLiveData<Boolean?>()
    val navigateToWriterListFragment: LiveData<Boolean?>
        get() = _navigateToWriterListFragment
    fun onViewWritersClicked(){
        _navigateToWriterListFragment.value= true
    }
    fun doneNavigatingToWritersFragment(){
        _navigateToWriterListFragment.value= null
    }

    // navigate to ClientList Fragment
    private val _navigateToClientListFragment = MutableLiveData<Boolean?>()
    val navigateToClientListFragment: LiveData<Boolean?>
        get() = _navigateToClientListFragment
    fun onViewClientsClicked(){
        _navigateToClientListFragment.value= true
    }
    fun doneNavigatingToClientsFragment(){
        _navigateToClientListFragment.value= null
    }



}