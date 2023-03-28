package com.example.taskmanagementapp.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Note::class],version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDAO() : NoteDAO
}