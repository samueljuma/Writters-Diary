package com.devjay.writtersdiary.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.devjay.writtersdiary.data.entities.WriterTask

@Dao
interface WriterTaskDao {
    // insert writerTask
    @Insert
    suspend fun insert(writerTask: WriterTask)

    @Delete
    suspend fun deleteWriterTask(writerTask: WriterTask)

    // update writerTask
    @Update
    fun update(writerTask: WriterTask)

    // get a specific writerTask by ID
    @Query("SELECT *FROM writers_tasks_table WHERE taskID = :key")
    fun getWriterTask (key: Long): LiveData<WriterTask>

    //get all writer's Tasks
    @Query("SELECT * FROM writers_tasks_table WHERE writerID_assigned = :key ORDER BY taskID DESC")
    fun getAllWritersTasks(key: Long): LiveData<List<WriterTask>>
}