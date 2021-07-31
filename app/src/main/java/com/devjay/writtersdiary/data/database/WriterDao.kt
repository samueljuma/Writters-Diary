package com.devjay.writtersdiary.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.devjay.writtersdiary.data.entities.Writer
import kotlinx.coroutines.flow.Flow

@Dao
interface WriterDao {

    // insert writer
    @Insert
    suspend fun insert(writer: Writer)

    @Delete
    suspend fun deleteWriter(writer: Writer)

    // get a specific writer by ID
    @Query ("SELECT *FROM writers_table WHERE writerID = :key")
    fun getWriter (key: Long): Flow<Writer>

    //get all writers
    @Query ("SELECT * FROM writers_table ORDER BY writerID DESC")
    fun getAllWriters(): Flow<List<Writer>>

}