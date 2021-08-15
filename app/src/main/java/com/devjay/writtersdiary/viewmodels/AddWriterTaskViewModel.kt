package com.devjay.writtersdiary.viewmodels

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
class AddWriterTaskViewModel @Inject constructor(
    private val writerTaskRepository: WriterTaskRepository,
    private val clientTaskRepository: ClientTaskRepository
): ViewModel(){

    fun addWriterTask(writerTask: WriterTask){
        viewModelScope.launch {
            writerTaskRepository.addTaskToDatabase(writerTask)
        }
    }

    fun addClientTask(clientTask: ClientTask){
        viewModelScope.launch {
            clientTaskRepository.addClientTaskToDatabase(clientTask)
        }
    }
}