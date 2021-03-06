package com.devjay.writtersdiary.data.repository

import com.devjay.writtersdiary.data.database.ClientDao
import com.devjay.writtersdiary.data.entities.Client
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ClientRepository @Inject constructor(
    private val clientDao: ClientDao
){

    suspend fun addClientToDatabase(client: Client){
        clientDao.insert(client)
    }

    suspend fun removeClientFromDatabase(client: Client){
        clientDao.deleteClient(client)
    }

    suspend fun deleteAllClients(){
        clientDao.deleteAllClients()
    }

    suspend fun updatePendingTasks(clientId: Long, value: Int){
        clientDao.updatePendingTasks(clientId,value)
    }

    suspend fun updateCompletedTasks(clientId: Long, value: Int){
        clientDao.updateCompleteTasks(clientId,value)
    }

    fun getClient(clientId: Long) = clientDao.getClient(clientId)

    fun getAllClients() = clientDao.getAllClients()


}