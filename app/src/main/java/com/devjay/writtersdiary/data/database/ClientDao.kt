package com.devjay.writtersdiary.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.devjay.writtersdiary.data.entities.Client

@Dao
interface ClientDao {
    // insert client
    @Insert
    suspend fun insert(client:Client)

    // delete client
    @Delete
    suspend fun deleteClient (client: Client)

    // update client
    @Update
    fun update(client: Client)

    // get a specific client by ID
    @Query("SELECT *FROM clients_table WHERE clientID = :key")
    fun getClient (key: Long): LiveData<Client>

    //get all clients
    @Query("SELECT * FROM clients_table ORDER BY clientID DESC")
    fun getAllClients(): LiveData<List<Client>>
}