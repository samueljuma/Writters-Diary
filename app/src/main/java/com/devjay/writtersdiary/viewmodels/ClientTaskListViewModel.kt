package com.devjay.writtersdiary.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.devjay.writtersdiary.data.entities.ClientTask
import com.devjay.writtersdiary.data.repository.ClientTaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ClientTaskListViewModel @Inject constructor(
    private val clientTaskRepository: ClientTaskRepository
): ViewModel(){

    fun getAllClientsTasks(clientId: Long): LiveData<List<ClientTask>>{
        return clientTaskRepository.getAllClientsTasks(clientId).asLiveData()
    }

    private val _navigateToAddClientTask = MutableLiveData<Boolean?>()
    val navigateToAddClientTask: LiveData<Boolean?>
        get() = _navigateToAddClientTask
    fun onAddFabClicked() {
        _navigateToAddClientTask.value = true
    }
    fun doneNavigatingToAddTasks(){
        _navigateToAddClientTask.value =null
    }
}