package com.devjay.writtersdiary.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devjay.writtersdiary.data.entities.Writer
import com.devjay.writtersdiary.data.repository.WriterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddWritersViewModel @Inject constructor(
    private val writerRepository: WriterRepository
): ViewModel(){


     fun addWriter(writer: Writer){
         viewModelScope.launch {
             writerRepository.addWriterToDatabase(writer)
         }

    }
}