package com.devjay.writtersdiary.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.devjay.writtersdiary.data.entities.Client
import com.devjay.writtersdiary.data.entities.ClientTask
import com.devjay.writtersdiary.data.entities.Writer
import com.devjay.writtersdiary.data.entities.WriterTask

@Database(entities = [Writer::class, WriterTask::class, Client::class, ClientTask::class], version = 1, exportSchema = false)
abstract class AppDatabase (): RoomDatabase(){
    abstract val writerDao: WriterDao
    abstract val writerTaskDao: WriterTaskDao
    abstract val clientDao: ClientDao
    abstract  val clientTaskDao: ClientTask


}