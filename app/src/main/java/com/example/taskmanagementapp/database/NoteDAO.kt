package com.example.taskmanagementapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDAO {
    @Query("SELECT * FROM notes_table")
    fun getAllNotes() : LiveData<List<Note>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(arg : Note)

    @Delete
    suspend fun delete(arg : Note)

    @Update
    suspend fun update(arg : Note)
}