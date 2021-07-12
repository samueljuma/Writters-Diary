package com.devjay.writtersdiary.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "clients_table")
data class Client (

    @PrimaryKey(autoGenerate = true) var clientID: Long = 0L,
    @ColumnInfo(name = "client_name") var name: String,
    @ColumnInfo(name = "completed_tasks") var completedTasks: Int = 0,
    @ColumnInfo(name = "pending_tasks") var pendingTasks: Int = 0
         )