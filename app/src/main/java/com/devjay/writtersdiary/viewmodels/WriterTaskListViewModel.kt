package com.devjay.writtersdiary.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.devjay.writtersdiary.data.entities.WriterTask
import com.devjay.writtersdiary.data.repository.WriterTaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WriterTaskListViewModel @Inject constructor(
    private val writerTaskRepository: WriterTaskRepository
): ViewModel(){

    fun getAllWriterTasks(writerId: Long): LiveData<List<WriterTask>>{
        return writerTaskRepository.getAllWritersTasks(writerId).asLiveData()
    }
}