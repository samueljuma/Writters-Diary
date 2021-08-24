package com.devjay.writtersdiary.viewmodels

import androidx.lifecycle.*
import com.devjay.writtersdiary.data.entities.WriterTask
import com.devjay.writtersdiary.data.repository.WriterTaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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

    private val _navigateToUpdateWriterTask = MutableLiveData<Long?>()
    val navigateToUpdateWriterTask: LiveData<Long?>
        get() = _navigateToUpdateWriterTask

    fun onWriterTaskUpdateClicked(Id: Long) {
        _navigateToUpdateWriterTask.value = Id
    }
    fun doneNavigatingToUpdateWriterTask(){
        _navigateToUpdateWriterTask.value =null
    }

    private val _deleteWriterTask = MutableLiveData<WriterTask?>()
    val deleteWriterTask: LiveData<WriterTask?>
        get() = _deleteWriterTask

    fun onclickDeleteWriterTask(writerTask: WriterTask){
        _deleteWriterTask.value = writerTask
    }

    fun deleteWriterTask(writerTask: WriterTask){
        viewModelScope.launch {
            writerTaskRepository.removeTaskFromDatabase(writerTask)
        }
    }
}