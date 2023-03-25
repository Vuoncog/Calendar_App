package com.example.taskmanagementapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.withContext
import kotlin.coroutines.coroutineContext


@Database(version = 1, entities = [Note::class])
abstract class NoteDatabase : RoomDatabase() {
    abstract fun getNoteDAO() : NoteDAO
    companion object{
        @Volatile
        var instance : NoteDatabase? = null
        fun getNoteDatabase(context: Context) : NoteDatabase{
            if(instance == null)
                instance = Room.databaseBuilder(context, NoteDatabase::class.java, "notes_database").build()
            return instance as NoteDatabase
        }
    }
}