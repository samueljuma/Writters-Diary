package com.devjay.writtersdiary.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "writers_table")
data class Writer(

    @PrimaryKey(autoGenerate = true) var writerID: Long = 0L,
    @ColumnInfo(name ="writer_name") var name: String,
    @ColumnInfo(name = "completed_tasks") var completedTasks: Int = 0,
    @ColumnInfo(name = "pending_tasks") var pendingTasks: Int = 0
)
