package com.devjay.writtersdiary.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.devjay.writtersdiary.data.entities.ClientTask
import com.devjay.writtersdiary.data.entities.WriterTask
import com.devjay.writtersdiary.data.repository.ClientTaskRepository
import com.devjay.writtersdiary.data.repository.WriterTaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UpdateTaskViewModel @Inject constructor(
    private val clientTaskRepository: ClientTaskRepository,
    private val writerTaskRepository: WriterTaskRepository
):ViewModel() {

   fun getWriterTask (taskId: Long): LiveData<WriterTask>{
        return writerTaskRepository.getWriterTask(taskId).asLiveData()
   }
    fun getClientTask(taskId: Long):LiveData<ClientTask> {
        return clientTaskRepository.getClientTask(taskId).asLiveData()
    }


}