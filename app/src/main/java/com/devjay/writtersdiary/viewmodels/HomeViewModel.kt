package com.devjay.writtersdiary.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.devjay.writtersdiary.data.entities.Writer
import com.devjay.writtersdiary.data.repository.ClientRepository
import com.devjay.writtersdiary.data.repository.WriterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val clientRepository: ClientRepository,
    private val writerRepository: WriterRepository
): ViewModel() {

    val clients = clientRepository.getAllClients().asLiveData()// This line doesnt work


}