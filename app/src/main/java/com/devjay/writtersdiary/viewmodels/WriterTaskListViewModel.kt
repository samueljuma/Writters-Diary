package com.devjay.writtersdiary.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    private val _navigateToAddWriterTask = MutableLiveData<Boolean?>()
    val navigateToAddWriterTask: LiveData<Boolean?>
        get() = _navigateToAddWriterTask
    fun onAddFabClicked() {
        _navigateToAddWriterTask.value = true
    }
    fun doneNavigatingToAddTasks(){
        _navigateToAddWriterTask.value =null
    }
}