package com.example.taskmanagementapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.taskmanagementapp.database.converter.DateConverter
import com.example.taskmanagementapp.database.converter.ListEventConverter
import com.example.taskmanagementapp.database.converter.ListToDoTaskConverter

@Database(entities = [Note::class],version = 1, exportSchema = false)
@TypeConverters(DateConverter::class, ListToDoTaskConverter::class, ListEventConverter::class)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDAO() : NoteDAO
}