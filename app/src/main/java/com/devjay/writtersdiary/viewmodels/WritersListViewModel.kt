package com.devjay.writtersdiary.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.devjay.writtersdiary.data.entities.WriterTask
import com.devjay.writtersdiary.data.repository.WriterRepository
import com.devjay.writtersdiary.data.repository.WriterTaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WritersListViewModel @Inject constructor(
    private val writerRepository: WriterRepository,
    private val writerTaskRepository: WriterTaskRepository
): ViewModel(){
    val listOfWriters = writerRepository.getAllWriters().asLiveData()

    fun getAllPendingTasks(writerId: Long): LiveData<List<WriterTask>>{
        return writerTaskRepository.getWriterPendingOrCompleteTasks(writerId, false).asLiveData()
    }
    fun getAllCompleteTasks(writerId: Long): LiveData<List<WriterTask>>{
        return writerTaskRepository.getWriterPendingOrCompleteTasks(writerId, false).asLiveData()
    }

    /**
     * NAVIGATION
     */
    // navigation to writersList Fragment
    private val _navigateToAddWriterFragment = MutableLiveData<Boolean?>()
    val navigateToAddWriterFragment: LiveData<Boolean?>
        get() = _navigateToAddWriterFragment
    fun onFabClicked(){
        _navigateToAddWriterFragment.value= true
    }
    fun doneNavigatingToAddWritersFragment(){
        _navigateToAddWriterFragment.value= null
    }

    private val _navigateToWriterTasks = MutableLiveData<Long?>()
    val navigateToWriterTasks: LiveData<Long?>
        get() = _navigateToWriterTasks
    fun onViewWriterClicked(Id: Long) {
        _navigateToWriterTasks.value = Id
    }
    fun doneNavigatingToWriterTasks(){
        _navigateToWriterTasks.value =null
    }
}