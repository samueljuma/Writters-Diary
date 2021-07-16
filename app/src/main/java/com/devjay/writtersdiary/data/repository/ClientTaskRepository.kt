package com.devjay.writtersdiary.data.repository

import com.devjay.writtersdiary.data.database.ClientTaskDao
import com.devjay.writtersdiary.data.entities.ClientTask
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ClientTaskRepository @Inject constructor(
    private val clientTaskDao: ClientTaskDao
){
    suspend fun addClientTaskToDatabase(clientTask: ClientTask){
        clientTaskDao.insert(clientTask)
    }

    suspend fun removeClientTaskFromDatabase(clientTask: ClientTask){
        clientTaskDao.deleteClientTask(clientTask)
    }

    suspend fun updateClientTaskStatus(status: String, taskId: Long){
        clientTaskDao.updateClientTask(status,taskId)
    }

    fun getClientTask(taskId: Long){
        clientTaskDao.getClientTask(taskId)
    }

    fun getAllClientsTasks (clientId: Long){
        clientTaskDao.getAllClientsTasks(clientId)
    }

}
