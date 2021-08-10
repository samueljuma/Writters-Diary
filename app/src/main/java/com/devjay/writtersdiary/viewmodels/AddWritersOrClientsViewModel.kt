package com.devjay.writtersdiary.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devjay.writtersdiary.data.entities.Client
import com.devjay.writtersdiary.data.entities.Writer
import com.devjay.writtersdiary.data.repository.ClientRepository
import com.devjay.writtersdiary.data.repository.WriterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddWritersOrClientsViewModel @Inject constructor(
    private val writerRepository: WriterRepository,
    private val clientRepository: ClientRepository
): ViewModel(){

   //add writer
     fun addWriter(writer: Writer){
         viewModelScope.launch {
             writerRepository.addWriterToDatabase(writer)
         }
    }
    // add client
    fun addClient(client: Client){
        viewModelScope.launch {
            clientRepository.addClientToDatabase(client)
        }
    }


}