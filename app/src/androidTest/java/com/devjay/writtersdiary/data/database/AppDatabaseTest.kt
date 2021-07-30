package com.devjay.writtersdiary.data.database

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.devjay.writtersdiary.data.entities.Writer
import junit.framework.TestCase
import com.google.common.truth.Truth.assertThat;
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AppDatabaseTest : TestCase(){

    private lateinit var db: AppDatabase
    private lateinit var writerTaskDao: WriterTaskDao
    private lateinit var writerDao: WriterDao
    private lateinit var clientTaskDao: ClientTaskDao
    private lateinit var clientDao: ClientDao

    @Before
     fun createDb() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()

        writerDao = db.writerDao
        writerTaskDao = db.writerTaskDao
        clientDao = db.clientDao
        clientTaskDao = db.clientTaskDao
    }

    @Test
    fun testWriterDao () = runBlocking{

        val writer = Writer(2,"juma",3,2);
        writerDao.insert(writer)

        val writers = writerDao.getAllWriters()

        assertThat(writer).isIn(writers)

    }

    @After
    fun closeDb(){
        db.close()
    }

}