package com.devjay.writtersdiary.viewmodels

import androidx.lifecycle.*
import com.devjay.writtersdiary.data.entities.WriterTask
import com.devjay.writtersdiary.data.repository.WriterRepository
import com.devjay.writtersdiary.data.repository.WriterTaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
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
    fun getAllCompletedTasks (writerId: Long): LiveData<List<WriterTask>> {
        return writerTaskRepository.getWriterPendingOrCompleteTasks(writerId, false).asLiveData()
    }

    fun updatePendingTasks(writerId:Long, value:Int){
        viewModelScope.launch {
            writerRepository.updatePendingTasks(writerId,value)
        }
    }
    fun updateCompletedTasks(writerId:Long, value:Int){
        viewModelScope.launch {
            writerRepository.updateCompletedTasks(writerId,value)
        }
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