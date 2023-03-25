package com.example.taskmanagementapp.database

import android.app.Application
import androidx.lifecycle.LiveData

class Repository(app : Application) {
    private val noteDAO : NoteDAO
    init {
        val noteDatabase : NoteDatabase = NoteDatabase.getNoteDatabase(app)
        noteDAO = noteDatabase.getNoteDAO()
    }

    suspend fun insert(note : Note) = noteDAO.insert(note)
    suspend fun update(note : Note) = noteDAO.update(note)
    suspend fun delete(note : Note) = noteDAO.delete(note)
    fun getAllNotes() : LiveData<List<Note>> = noteDAO.getAllNotes()

}