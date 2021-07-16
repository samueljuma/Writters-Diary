package com.devjay.writtersdiary.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.devjay.writtersdiary.data.entities.ClientTask

@Dao
interface ClientTaskDao {
    // insert clientTask
    @Insert
    suspend fun insert(clientTask: ClientTask)

    @Delete
    suspend fun deleteClientTask(clientTask: ClientTask)

    // update clientTask
    @Query("UPDATE client_tasks_table SET task_status = :status WHERE taskID = :taskID")
    suspend fun updateClientTask(status: String, taskID: Long)

    // get a specific clientTask by ID
    @Query("SELECT *FROM client_tasks_table WHERE taskID = :key")
    fun getClientTask (key: Long): LiveData<ClientTask>

    //get all client's Tasks
    @Query("SELECT * FROM client_tasks_table WHERE assigned_by = :key ORDER BY taskID DESC")
    fun getAllClientsTasks(key: Long): LiveData<List<ClientTask>>
}