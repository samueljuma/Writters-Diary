package com.devjay.writtersdiary.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.devjay.writtersdiary.data.repository.ClientRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ClientsListViewModel @Inject constructor(
    private val clientRepository: ClientRepository
): ViewModel(){
    val listOfClients = clientRepository.getAllClients().asLiveData()
}