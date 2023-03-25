package com.example.taskmanagementapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
class Note(
    var title: String,
    var description: String,
    var isDone: Boolean
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    var index = 0
}