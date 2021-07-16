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
    @Update
    fun update(clientTask: ClientTask)

    // get a specific clientTask by ID
    @Query("SELECT *FROM client_tasks_table WHERE taskID = :key")
    fun getWriterTask (key: Long): LiveData<ClientTask>

    //get all writer's Tasks
    @Query("SELECT * FROM client_tasks_table WHERE assigned_by = :key ORDER BY taskID DESC")
    fun getAllWritersTasks(key: Long): LiveData<List<ClientTask>>
}