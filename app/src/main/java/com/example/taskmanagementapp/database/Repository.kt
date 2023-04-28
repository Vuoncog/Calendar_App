package com.example.taskmanagementapp.database

import kotlinx.coroutines.flow.Flow
import java.util.*
import javax.inject.Inject

class Repository @Inject constructor(private val noteDAO: NoteDAO) {

    suspend fun insert(note: Note) = noteDAO.insert(note)
    suspend fun update(note: Note) = noteDAO.update(note)
    suspend fun delete(note: Note) = noteDAO.delete(note)
    val getAllNotes: Flow<List<Note>> = noteDAO.getAllNotes()
    fun getSelectedRow(dateSelected: Date): Flow<Note> {
        return noteDAO.getSelectedRow(dateSelected = dateSelected)
    }
}