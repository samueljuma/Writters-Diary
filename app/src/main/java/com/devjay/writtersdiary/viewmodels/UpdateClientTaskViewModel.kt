package com.devjay.writtersdiary.viewmodels

import androidx.lifecycle.*
import com.devjay.writtersdiary.data.entities.ClientTask
import com.devjay.writtersdiary.data.entities.WriterTask
import com.devjay.writtersdiary.data.repository.ClientTaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateClientTaskViewModel @Inject constructor(
    private val clientTaskRepository: ClientTaskRepository
): ViewModel() {
    fun getClientTask (taskId: Long): LiveData<ClientTask> {
        return clientTaskRepository.getClientTask(taskId).asLiveData()
    }

    fun updateClientTask(title: String, orderNo: String, wordCount: Int, amount: Double,isComplete: Boolean,isPaid: Boolean, taskId: Long){
        viewModelScope.launch {
            clientTaskRepository.updateTask(title,orderNo,wordCount,amount,isComplete,isPaid,taskId)
        }
    }

    // update ClientTask and Navigate Back
    private val _updateTaskAndNavigateBackToClientTaskList = MutableLiveData<ClientTask?>()
    val updateTaskAndNavigateBackToClientTaskList: LiveData<ClientTask?>
        get() = _updateTaskAndNavigateBackToClientTaskList
    fun onUpdateClientTaskClicked(clientTask: ClientTask){
        _updateTaskAndNavigateBackToClientTaskList.value =clientTask
    }
    fun doneNavigatingBackToClientTaskList(){
        _updateTaskAndNavigateBackToClientTaskList.value= null
    }

    //Cancel updating ClientTask and Navigate back
    private val _cancelUpdatingTaskAndNavigateBackToClientTaskList = MutableLiveData<Boolean?>()
    val cancelUpdatingTaskAndNavigateBackToClientTaskList: LiveData<Boolean?>
        get() = _cancelUpdatingTaskAndNavigateBackToClientTaskList
    fun onCancelUpdateClientTaskClicked(){
        _cancelUpdatingTaskAndNavigateBackToClientTaskList.value =true
    }
    fun doneCancelingAndNavigatingBackToClientTaskList(){
        _cancelUpdatingTaskAndNavigateBackToClientTaskList.value= null
    }
}