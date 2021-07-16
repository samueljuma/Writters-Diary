package com.devjay.writtersdiary.data.repository

import com.devjay.writtersdiary.data.database.WriterDao
import com.devjay.writtersdiary.data.entities.Writer
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class WriterRepository @Inject constructor(
    private val writerDao: WriterDao){

    suspend fun addWriterToDatabase(writer:Writer){
        writerDao.insert(writer)
    }

    suspend fun removeWriterFromDatabase(writer: Writer){
        writerDao.deleteWriter(writer)
    }

    fun getWriter(id: Long){
        writerDao.getWriter(id)
    }

    fun getAllWriters(){
        writerDao.getAllWriters()
    }



}