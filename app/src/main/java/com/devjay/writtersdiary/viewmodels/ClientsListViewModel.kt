package com.devjay.writtersdiary.viewmodels

import androidx.lifecycle.*
import com.devjay.writtersdiary.data.entities.ClientTask
import com.devjay.writtersdiary.data.entities.WriterTask
import com.devjay.writtersdiary.data.repository.ClientRepository
import com.devjay.writtersdiary.data.repository.ClientTaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClientsListViewModel @Inject constructor(
    private val clientRepository: ClientRepository,
    private val clientTaskRepository: ClientTaskRepository
): ViewModel(){
    val listOfClients = clientRepository.getAllClients().asLiveData()


    fun getAllPendingTasks(clientId: Long): LiveData<List<ClientTask>>{
        return clientTaskRepository.getClientPendingOrCompleteTasks(clientId, false).asLiveData()
    }
    fun getAllCompletedTasks (clientId: Long): LiveData<List<ClientTask>> {
        return clientTaskRepository.getClientPendingOrCompleteTasks(clientId, true).asLiveData()
    }

    fun updatePendingTasks(clientId:Long, value:Int){
        viewModelScope.launch {
            clientRepository.updatePendingTasks(clientId,value)
        }
    }
    fun updateCompletedTasks(clientId:Long, value:Int){
        viewModelScope.launch {
            clientRepository.updateCompletedTasks(clientId,value)
        }
    }

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