package com.example.taskmanagementapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.withContext
import kotlin.coroutines.coroutineContext


@Database(entities = [Note::class],version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDAO() : NoteDAO
}