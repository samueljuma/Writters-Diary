package com.devjay.writtersdiary.data.database

import androidx.room.*
import com.devjay.writtersdiary.data.entities.Client
import kotlinx.coroutines.flow.Flow

@Dao
interface ClientDao {
    // insert client
    @Insert
    suspend fun insert(client:Client)

    // delete client
    @Delete
    suspend fun deleteClient (client: Client)
    @Query ("UPDATE clients_table SET pending_tasks = :value WHERE clientID = :clientId")
    suspend fun updatePendingTasks(clientId: Long, value: Int)

    @Query ("UPDATE clients_table SET completed_tasks = :value WHERE  clientID = :clientId")
    suspend fun updateCompleteTasks(clientId: Long, value: Int)

    // get a specific client by ID
    @Query("SELECT *FROM clients_table WHERE clientID = :key")
    fun getClient (key: Long): Flow<Client>

    //get all clients
    @Query("SELECT * FROM clients_table ORDER BY clientID DESC")
    fun getAllClients(): Flow<List<Client>>
}