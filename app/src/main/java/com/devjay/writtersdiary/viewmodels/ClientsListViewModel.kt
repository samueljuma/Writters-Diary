package com.devjay.writtersdiary.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.devjay.writtersdiary.data.repository.ClientRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ClientsListViewModel @Inject constructor(
    private val clientRepository: ClientRepository
): ViewModel(){
    val listOfClients = clientRepository.getAllClients().asLiveData()

    /**
     * NAVIGATION
     */
    // navigation to writersList Fragment
    private val _navigateToAddClientFragment = MutableLiveData<Boolean?>()
    val navigateToAddClientFragment : LiveData<Boolean?>
        get() = _navigateToAddClientFragment
    fun onFabClicked(){
        _navigateToAddClientFragment.value= true
    }
    fun doneNavigatingToAddClientsFragment(){
        _navigateToAddClientFragment.value= null
    }

    private val _navigateToClientTasks = MutableLiveData<Long?>()
    val navigateToClientTasks: LiveData<Long?>
        get() = _navigateToClientTasks
    fun onViewClientTasksClicked(Id: Long) {
        _navigateToClientTasks.value = Id
    }
    fun doneNavigatingToClientTasks(){
        _navigateToClientTasks.value =null
    }
}