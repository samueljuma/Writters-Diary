package com.devjay.writtersdiary.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.devjay.writtersdiary.data.entities.ClientTask
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

    // get a specific clientTask by ID
    @Query("SELECT * FROM client_tasks_table WHERE taskID = :taskId")
    fun getClientTask (taskId: Long): Flow<ClientTask>

    //get all client's Tasks
    @Query("SELECT * FROM client_tasks_table WHERE assigned_by = :assignedBy ORDER BY taskID DESC")
    fun getAllClientsTasks(assignedBy: Long): Flow<List<ClientTask>>
}