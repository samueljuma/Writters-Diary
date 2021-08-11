package com.devjay.writtersdiary.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.devjay.writtersdiary.data.entities.WriterTask
import kotlinx.coroutines.flow.Flow

@Dao
interface WriterTaskDao {
    // insert writerTask
    @Insert
    suspend fun insert(writerTask: WriterTask)

    @Delete
    suspend fun deleteWriterTask(writerTask: WriterTask)

    // update writerTask if complete
    @Query("UPDATE writers_tasks_table SET is_complete = :isComplete WHERE taskID = :taskID")
    suspend fun updateTaskComplete(isComplete: Boolean, taskID: Long)

    //update writerTask if paid
    @Query("UPDATE writers_tasks_table SET is_paid = :isPaid WHERE taskID = :taskID")
    suspend fun updateTaskPaid(isPaid: Boolean, taskID: Long)

    // get a specific writerTask by ID
    @Query("SELECT *FROM writers_tasks_table WHERE taskID = :taskId")
    fun getWriterTask (taskId: Long): Flow<WriterTask>

    //get all writer's Tasks
    @Query("SELECT * FROM writers_tasks_table WHERE writerID_assigned = :assignedTo ORDER BY taskID DESC")
    fun getAllWritersTasks(assignedTo: Long): Flow<List<WriterTask>>
}