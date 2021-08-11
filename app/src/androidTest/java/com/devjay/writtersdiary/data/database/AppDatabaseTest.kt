package com.devjay.writtersdiary.data.database

import androidx.lifecycle.asFlow
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.devjay.writtersdiary.data.entities.Client
import com.devjay.writtersdiary.data.entities.ClientTask
import com.devjay.writtersdiary.data.entities.Writer
import com.devjay.writtersdiary.data.entities.WriterTask
import junit.framework.TestCase
import com.google.common.truth.Truth.assertThat;
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.flow.toSet
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
        var writers = writerDao.getAllWriters().first()

        assertThat(writerDao.getWriter(2).first()).isEqualTo(writer)
        assertThat(writer).isIn(writers)

        writerDao.deleteWriter(writer)
        writers = writerDao.getAllWriters().first()

        assertThat(writer).isNotIn(writers)
    }
    @Test
    fun testClientDao () = runBlocking{

        val client = Client(2,"juma",3,2);
        clientDao.insert(client)
        var clients = clientDao.getAllClients().first()

        assertThat(clientDao.getClient(2).first()).isEqualTo(client)
        assertThat(client).isIn(clients)

        clientDao.deleteClient(client)
        clients = clientDao.getAllClients().first()

        assertThat(client).isNotIn(clients)

        assertThat(clientDao.getAllClients().first().size).isEqualTo(0)

    }
    @Test
    fun testWriterTaskDao () = runBlocking{

        val writerTask = WriterTask(3,System.currentTimeMillis() ,3,"234","Assignment",
            3,2,3000.0,false,false)
        // insert
        writerTaskDao.insert(writerTask)
        var writersTasks = writerTaskDao.getAllWritersTasks(3).first()
        assertThat(writerTaskDao.getWriterTask(3).first()).isIn(writersTasks)
        //update
        writerTaskDao.updateTaskPaid(true,3)
        assertThat(writerTaskDao.getWriterTask(3).first().isPaid).isTrue()

        writerTaskDao.updateTaskComplete(true,3)
        assertThat(writerTaskDao.getWriterTask(3).first().isComplete).isTrue()
        //delete
        writerTaskDao.deleteWriterTask(writerTaskDao.getWriterTask(3).first())
        writersTasks = writerTaskDao.getAllWritersTasks(3).first()
        assertThat(writerTaskDao.getWriterTask(3)).isNotIn(writersTasks)
    }

    @Test
    fun testClientTaskDao () = runBlocking{

        val clientTask = ClientTask(3,System.currentTimeMillis(),3,"Assignment",
            "Business Assignment",3000,2,300.0,false,false)
        // insert
        clientTaskDao.insert(clientTask)
        var clientsTasks = clientTaskDao.getAllClientsTasks(3).first()
        assertThat(clientTaskDao.getClientTask(3).first()).isIn(clientsTasks)
        //update
        clientTaskDao.updateClientTaskPaid(true,3)
        assertThat(clientTaskDao.getClientTask(3).first().isPaid).isTrue()

        clientTaskDao.updateClientTaskComplete(true,3)
        assertThat(clientTaskDao.getClientTask(3).first().isComplete).isTrue()
        //delete
        clientTaskDao.deleteClientTask(clientTaskDao.getClientTask(3).first())
        clientsTasks = clientTaskDao.getAllClientsTasks(3).first()
        assertThat(clientTaskDao.getClientTask(3)).isNotIn(clientsTasks)
    }

    @After
    fun closeDb(){
        db.close()
    }

}