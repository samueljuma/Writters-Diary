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

    // update writerTask
    @Query("UPDATE writers_tasks_table SET task_status = :status WHERE taskID = :taskID")
    suspend fun updateTask(status: String, taskID: Long)

    // get a specific writerTask by ID
    @Query("SELECT *FROM writers_tasks_table WHERE taskID = :taskId")
    fun getWriterTask (taskId: Long): Flow<WriterTask>

    //get all writer's Tasks
    @Query("SELECT * FROM writers_tasks_table WHERE writerID_assigned = :assignedBy ORDER BY taskID DESC")
    fun getAllWritersTasks(assignedBy: Long): Flow<List<WriterTask>>
}