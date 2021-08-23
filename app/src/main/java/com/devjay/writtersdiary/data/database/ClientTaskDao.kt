package com.devjay.writtersdiary.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.devjay.writtersdiary.data.entities.ClientTask
import com.devjay.writtersdiary.data.entities.WriterTask
import kotlinx.coroutines.flow.Flow

@Dao
interface ClientTaskDao {
    // insert clientTask
    @Insert
    suspend fun insert(clientTask: ClientTask)

    @Delete
    suspend fun deleteClientTask(clientTask: ClientTask)

    // update clientTask if Complete
    @Query("UPDATE client_tasks_table SET is_complete = :isComplete WHERE taskID = :taskID")
    suspend fun updateClientTaskComplete(isComplete: Boolean, taskID: Long)

    // update clientTask if Paid
    @Query("UPDATE client_tasks_table SET is_paid = :isPaid WHERE taskID = :taskID")
    suspend fun updateClientTaskPaid(isPaid: Boolean, taskID: Long)

    @Query("UPDATE client_tasks_table SET task_title= :title,order_number= :orderNo,number_of_words=:wordCount,amount_payable=:amount,is_complete=:isComplete,is_paid=:isPaid WHERE taskID= :taskId" )
    suspend fun updateTask(title: String, orderNo: String, wordCount: Int, amount: Double,isComplete: Boolean,isPaid: Boolean, taskId: Long)

    // get a specific clientTask by ID
    @Query("SELECT * FROM client_tasks_table WHERE taskID = :taskId")
    fun getClientTask (taskId: Long): Flow<ClientTask>

    @Query("SELECT * FROM client_tasks_table WHERE assigned_by =:clientId AND is_complete=:isComplete")
    fun getAllClientPendingOrCompleteTasks(clientId: Long, isComplete: Boolean): Flow<List<ClientTask>>

    //get all client's Tasks
    @Query("SELECT * FROM client_tasks_table WHERE assigned_by = :assignedBy ORDER BY taskID DESC")
    fun getAllClientsTasks(assignedBy: Long): Flow<List<ClientTask>>
}