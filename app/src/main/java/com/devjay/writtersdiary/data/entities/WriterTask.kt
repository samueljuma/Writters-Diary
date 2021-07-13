package com.devjay.writtersdiary.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "writers_tasks_table")
data class WriterTask(

    @PrimaryKey(autoGenerate = true) var taskID: Long = 0L,
    @ColumnInfo(name = "writerID_assigned") var writerAssigned: Long =0L,
    @ColumnInfo(name = "order_number") var orderNumber: String,
    @ColumnInfo(name = "task_title") var title: String,
    @ColumnInfo(name = "number_of_pages_words") var numberOfPagesOrWordCount: Int = 0,
    @ColumnInfo(name = "amount_payable") var amountPayable: Double = 0.0,
    @ColumnInfo(name = "task_status") var status: String
)