package com.devjay.writtersdiary.viewmodels

import androidx.lifecycle.*
import com.devjay.writtersdiary.data.entities.ClientTask
import com.devjay.writtersdiary.data.entities.WriterTask
import com.devjay.writtersdiary.data.repository.WriterTaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateWriterTaskViewModel @Inject constructor(
    private val writerTaskRepository: WriterTaskRepository
): ViewModel() {
    fun getWriterTask (taskId: Long): LiveData<WriterTask> {
        return writerTaskRepository.getWriterTask(taskId).asLiveData()
    }

    fun updateWriterTask(title: String, orderNo: String, wordCount: Int, amount: Double,isComplete: Boolean,isPaid: Boolean, taskId: Long){
        viewModelScope.launch {
            writerTaskRepository.updateTask(title,orderNo,wordCount,amount,isComplete,isPaid,taskId)
        }
    }

    private val _updateTaskAndNavigateBackToWritersTaskList = MutableLiveData<Boolean?>()
    val updateTaskAndNavigateBackToWritersTaskList: LiveData<Boolean?>
        get() = _updateTaskAndNavigateBackToWritersTaskList
    fun onUpdateWriterTaskClicked(){
        _updateTaskAndNavigateBackToWritersTaskList.value =true
    }
    fun doneNavigatingBackToWriterTaskList(){
        _updateTaskAndNavigateBackToWritersTaskList.value= null
    }

    private val _cancelUpdatingTaskAndNavigateBackToWritersTaskList = MutableLiveData<Boolean?>()
    val cancelUpdatingTaskAndNavigateBackToWritersTaskList: LiveData<Boolean?>
        get() = _cancelUpdatingTaskAndNavigateBackToWritersTaskList
    fun onCancelUpdateWriterTaskClicked(){
        _cancelUpdatingTaskAndNavigateBackToWritersTaskList.value =true
    }
    fun doneCancelingAndNavigatingBackToWriterTaskList(){
        _cancelUpdatingTaskAndNavigateBackToWritersTaskList.value= null
    }

}