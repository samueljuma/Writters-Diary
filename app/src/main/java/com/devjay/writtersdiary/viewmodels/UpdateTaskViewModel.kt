package com.devjay.writtersdiary.viewmodels

import androidx.lifecycle.ViewModel
import com.devjay.writtersdiary.data.repository.ClientTaskRepository
import com.devjay.writtersdiary.data.repository.WriterTaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UpdateTaskViewModel @Inject constructor(
    private val clientTaskRepository: ClientTaskRepository,
    private val writerTaskRepository: WriterTaskRepository
):ViewModel() {

}