package com.devjay.writtersdiary.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.devjay.writtersdiary.data.entities.Writer

@Dao
interface WriterDao {

    // insert writer
    @Insert
    suspend fun insert(writer: Writer)

    // update writer
    @Update
    fun update(writer: Writer)

    // get a specific writer by ID
    @Query ("SELECT *FROM writers_table WHERE writerID = :key")
    fun getWriter (key: Long): LiveData<Writer>

    //get all writers
    @Query ("SELECT * FROM writers_table ORDER BY writerID DESC")
    fun getAllWriters(): LiveData<List<Writer>>

}