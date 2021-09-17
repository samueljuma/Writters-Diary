package com.devjay.writtersdiary.data.database

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

    @Query("DELETE FROM writers_tasks_table")
    suspend fun deleteAllTasks()

    // update writerTask if complete
    @Query("UPDATE writers_tasks_table SET is_complete = :isComplete WHERE taskID = :taskID")
    suspend fun updateTaskComplete(isComplete: Boolean, taskID: Long)

    //update writerTask if paid
    @Query("UPDATE writers_tasks_table SET is_paid = :isPaid WHERE taskID = :taskID")
    suspend fun updateTaskPaid(isPaid: Boolean, taskID: Long)

    @Query("UPDATE writers_tasks_table SET task_title= :title,order_number= :orderNo,number_of_words=:wordCount,amount_payable=:amount,is_complete=:isComplete,is_paid=:isPaid WHERE taskID= :taskId" )
    suspend fun updateTask(title: String, orderNo: String, wordCount: Int, amount: Double,isComplete: Boolean,isPaid: Boolean, taskId: Long)

    @Query("DELETE FROM writers_tasks_table WHERE writerID_assigned= :writerId")
    suspend fun deleteAllTasksFromTheWriter(writerId: Long)

    // get a specific writerTask by ID
    @Query("SELECT *FROM writers_tasks_table WHERE taskID = :taskId")
    fun getWriterTask (taskId: Long): Flow<WriterTask>

    @Query("SELECT * FROM writers_tasks_table WHERE writerID_assigned =:writerId AND is_complete=:isComplete")
    fun getAllWriterPendingOrCompleteTasks(writerId: Long, isComplete: Boolean): Flow<List<WriterTask>>

    //get all writer's Tasks
    @Query("SELECT * FROM writers_tasks_table WHERE writerID_assigned = :assignedTo ORDER BY taskID DESC")
    fun getAllWritersTasks(assignedTo: Long): Flow<List<WriterTask>>
}