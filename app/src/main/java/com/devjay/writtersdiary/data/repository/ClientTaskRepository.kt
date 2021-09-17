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

    suspend fun deleteAllTasks(){
        clientTaskDao.deleteAllTasks()
    }

    suspend fun updateClientTaskComplete(isComplete: Boolean, taskId: Long){
        clientTaskDao.updateClientTaskComplete(isComplete,taskId)
    }

    suspend fun updateTask(title: String, orderNo: String, wordCount: Int, amount: Double,isComplete: Boolean,isPaid: Boolean, taskId: Long){
        clientTaskDao.updateTask(title,orderNo,wordCount,amount,isComplete,isPaid,taskId)
    }

    suspend fun updateClientTaskPaid(isPaid: Boolean, taskId: Long){
        clientTaskDao.updateClientTaskPaid(isPaid,taskId)
    }

    suspend fun deleteAllTasksFromTheClient(clientId: Long){
        clientTaskDao.deleteAllTasksFromTheClient(clientId)
    }

    fun getClientPendingOrCompleteTasks(clientId: Long, isComplete: Boolean) = clientTaskDao.getAllClientPendingOrCompleteTasks(clientId,isComplete)


    fun getClientTask(taskId: Long) = clientTaskDao.getClientTask(taskId)

    fun getAllClientsTasks (clientId: Long) = clientTaskDao.getAllClientsTasks(clientId)

}
