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

    // get a specific client by ID
    @Query("SELECT *FROM clients_table WHERE clientID = :key")
    fun getClient (key: Long): Flow<Client>

    //get all clients
    @Query("SELECT * FROM clients_table ORDER BY clientID DESC")
    fun getAllClients(): Flow<List<Client>>
}