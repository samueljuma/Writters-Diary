package com.devjay.writtersdiary.viewmodels

import androidx.lifecycle.ViewModel
import com.devjay.writtersdiary.data.entities.Writer
import com.devjay.writtersdiary.data.repository.WriterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddWritersViewModel @Inject constructor(
    private val writerRepository: WriterRepository
): ViewModel(){

    suspend fun addWriter(writer: Writer){
        writerRepository.addWriterToDatabase(writer)
    }
}