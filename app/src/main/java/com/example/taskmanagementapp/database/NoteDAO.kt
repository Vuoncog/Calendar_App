package com.example.taskmanagementapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteDAO {
    @Insert
    suspend fun insert(arg : Note)

    @Delete
    suspend fun delete(arg : Note)

    @Update
    suspend fun update(arg : Note)

    @Query("SELECT * FROM notes_table")
    fun getAllNotes() : LiveData<List<Note>>
}