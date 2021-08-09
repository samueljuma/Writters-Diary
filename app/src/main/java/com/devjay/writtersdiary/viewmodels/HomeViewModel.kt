package com.devjay.writtersdiary.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.devjay.writtersdiary.data.repository.ClientRepository
import com.devjay.writtersdiary.data.repository.WriterRepository
import com.devjay.writtersdiary.utils.formatWhoNumber
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val clientRepository: ClientRepository,
    private val writerRepository: WriterRepository
): ViewModel() {

    val clients = clientRepository.getAllClients().asLiveData()
    val writers = writerRepository.getAllWriters().asLiveData()


}