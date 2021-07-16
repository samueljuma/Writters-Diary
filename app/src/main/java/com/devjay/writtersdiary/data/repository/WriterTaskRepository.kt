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

    suspend fun updateWriterTaskStatus(status: String, taskId: Long){
        writerTaskDao.updateTask(status,taskId)
    }

    fun getWriterTask(taskId: Long){
        writerTaskDao.getWriterTask(taskId)
    }
    fun getAllWritersTasks(writerId: Long){
        writerTaskDao.getAllWritersTasks(writerId)
    }


}