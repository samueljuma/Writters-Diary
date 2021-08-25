package com.devjay.writtersdiary.data.repository

import com.devjay.writtersdiary.data.database.WriterTaskDao
import com.devjay.writtersdiary.data.entities.WriterTask
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WriterTaskRepository @Inject constructor(
    private val writerTaskDao: WriterTaskDao
) {

    suspend fun addTaskToDatabase(writerTask: WriterTask){
        writerTaskDao.insert(writerTask)
    }
    suspend fun removeTaskFromDatabase(writerTask: WriterTask){
        writerTaskDao.deleteWriterTask(writerTask)
    }

    suspend fun updateWriterTaskComplete(isComplete: Boolean, taskId: Long){
        writerTaskDao.updateTaskComplete(isComplete,taskId)
    }
    suspend fun updateWriterTaskPaid(isPaid: Boolean, taskId: Long){
        writerTaskDao.updateTaskPaid(isPaid,taskId)
    }

    suspend fun updateTask(title: String, orderNo: String, wordCount: Int, amount: Double,isComplete: Boolean,isPaid: Boolean, taskId: Long){
        writerTaskDao.updateTask(title,orderNo,wordCount,amount,isComplete,isPaid,taskId)
    }
    suspend fun deleteAllTasksFromTheWriter(writerId: Long){
        writerTaskDao.deleteAllTasksFromTheWriter(writerId)
    }

    fun getWriterPendingOrCompleteTasks(writerId: Long, isComplete: Boolean) = writerTaskDao.getAllWriterPendingOrCompleteTasks(writerId,isComplete)

    fun getWriterTask(taskId: Long) = writerTaskDao.getWriterTask(taskId)

    fun getAllWritersTasks(writerId: Long) = writerTaskDao.getAllWritersTasks(writerId)


}