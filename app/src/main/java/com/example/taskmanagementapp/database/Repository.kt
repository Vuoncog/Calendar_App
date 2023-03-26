package com.example.taskmanagementapp.database

import androidx.lifecycle.LiveData
import javax.inject.Inject

class Repository @Inject constructor(private val noteDAO: NoteDAO) {

    suspend fun insert(note : Note) = noteDAO.insert(note)
    suspend fun update(note : Note) = noteDAO.update(note)
    suspend fun delete(note : Note) = noteDAO.delete(note)
    val getAllNotes: LiveData<List<Note>> = noteDAO.getAllNotes()
}