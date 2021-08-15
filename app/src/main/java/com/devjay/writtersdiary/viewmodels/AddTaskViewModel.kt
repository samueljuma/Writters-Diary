package com.devjay.writtersdiary.viewmodels

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devjay.writtersdiary.data.entities.ClientTask
import com.devjay.writtersdiary.data.entities.WriterTask
import com.devjay.writtersdiary.data.repository.ClientTaskRepository
import com.devjay.writtersdiary.data.repository.WriterTaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTaskViewModel @Inject constructor(
    private val writerTaskRepository: WriterTaskRepository,
    private val clientTaskRepository: ClientTaskRepository
): ViewModel(){

    fun addWriterTask(writerId:Long,title:String,orderNo:String,wordCount:Int,amountPayable:Double){
        val writerTask =WriterTask(writerAssigned = writerId,title = title,orderNumber = orderNo,
            numberOfPagesOrWordCount = wordCount, amountPayable = amountPayable)
        viewModelScope.launch {
            writerTaskRepository.addTaskToDatabase(writerTask)
        }
    }

    fun addClientTask(clientTask: ClientTask){
        viewModelScope.launch {
            clientTaskRepository.addClientTaskToDatabase(clientTask)
        }
    }

    private val _addTaskAndNavigateBackToWriterTaskList = MutableLiveData<Boolean?>()
    val addTaskAndNavigateBackToWriterTaskList: LiveData<Boolean?>
        get() = _addTaskAndNavigateBackToWriterTaskList
    fun onAddWriterTaskClicked(){
        _addTaskAndNavigateBackToWriterTaskList.value= true
    }
    fun doneNavigatingBackToWriterTaskList(){
        _addTaskAndNavigateBackToWriterTaskList.value= null
    }

}