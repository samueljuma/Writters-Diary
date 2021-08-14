package com.devjay.writtersdiary.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.devjay.writtersdiary.data.repository.WriterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WritersListViewModel @Inject constructor(
    private val writerRepository: WriterRepository
): ViewModel(){
    val listOfWriters = writerRepository.getAllWriters().asLiveData()

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