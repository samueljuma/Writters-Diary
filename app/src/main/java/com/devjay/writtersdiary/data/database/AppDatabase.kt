package com.devjay.writtersdiary.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.devjay.writtersdiary.data.entities.Client
import com.devjay.writtersdiary.data.entities.ClientTask
import com.devjay.writtersdiary.data.entities.Writer
import com.devjay.writtersdiary.data.entities.WriterTask
import com.devjay.writtersdiary.utils.DATABASE_NAME

@Database(entities = [Writer::class, WriterTask::class, Client::class, ClientTask::class], version = 2, exportSchema = false)
abstract class AppDatabase (): RoomDatabase(){
    abstract val writerDao: WriterDao
    abstract val writerTaskDao: WriterTaskDao
    abstract val clientDao: ClientDao
    abstract  val clientTaskDao: ClientTaskDao

    companion object{
        @Volatile //ensures value of instance is always up to data
        private var INSTANCE: AppDatabase? =null

        fun getInstance(context: Context): AppDatabase{
            //synchronized ensures only one thread of execution at a time
            synchronized(this){
                var instance = INSTANCE //Kotlin Smart Cast
                if (instance ==null){
                    instance = Room.databaseBuilder(context.applicationContext,
                    AppDatabase::class.java, DATABASE_NAME)
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE =instance
                }
                return instance
            }
        }
    }
}