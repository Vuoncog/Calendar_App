package com.example.taskmanagementapp.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface NoteDAO {
    @Query("SELECT * FROM notes_table")
    fun getAllNotes() : Flow<List<Note>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(arg : Note)

    @Delete
    suspend fun delete(arg : Note)

    @Update
    suspend fun update(arg : Note)

    @Query("SELECT * FROM notes_table WHERE date=:dateSelected")
    fun getSelectedRow(dateSelected: Date): Flow<Note>
}