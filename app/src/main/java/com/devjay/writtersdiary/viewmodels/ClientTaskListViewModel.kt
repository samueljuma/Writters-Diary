package com.devjay.writtersdiary.viewmodels

import androidx.lifecycle.*
import com.devjay.writtersdiary.data.entities.ClientTask
import com.devjay.writtersdiary.data.entities.WriterTask
import com.devjay.writtersdiary.data.repository.ClientTaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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

    private val _navigateToUpdateClientTask = MutableLiveData<Long?>()
    val navigateToUpdateClientTask: LiveData<Long?>
        get() = _navigateToUpdateClientTask

    fun onClientTaskUpdateClicked(Id: Long) {
        _navigateToUpdateClientTask.value = Id
    }
    fun doneNavigatingToUpdateClientTask(){
        _navigateToUpdateClientTask.value =null
    }

    private val _deleteClientTask = MutableLiveData<ClientTask?>()
    val deleteClientTask: LiveData<ClientTask?>
        get() = _deleteClientTask

    fun onclickDeleteClientTask(clientTask: ClientTask){
        _deleteClientTask.value = clientTask
    }

    fun deleteClientTask(clientTask: ClientTask){
        viewModelScope.launch {
            clientTaskRepository.removeClientTaskFromDatabase(clientTask)
        }
    }
}