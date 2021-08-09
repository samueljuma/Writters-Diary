package com.devjay.writtersdiary.viewmodels

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
}