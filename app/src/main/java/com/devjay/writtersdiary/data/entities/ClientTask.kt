package com.devjay.writtersdiary.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "client_tasks_table")
data class ClientTask(

    @PrimaryKey(autoGenerate = true) var taskID: Long = 0L,
    @ColumnInfo(name ="time_created") var time_created: Long = System.currentTimeMillis(),
    @ColumnInfo(name = "assigned_by") var assignedBy: Long =0L,
    @ColumnInfo(name = "order_number") var orderNumber: String,
    @ColumnInfo(name = "task_title") var title: String,
    @ColumnInfo(name = "number_of_words") var numberOfPagesOrWordCount: Int = 0,
    @ColumnInfo(name = "page_count") var pageCount: Int = numberOfPagesOrWordCount/300,
    @ColumnInfo(name = "amount_payable") var amountPayable: Double = 0.0,
    @ColumnInfo(name = "is_complete") var isComplete: Boolean = false,
    @ColumnInfo(name = "is_paid") var isPaid: Boolean = false

)
