package com.example.taskmanagementapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.taskmanagementapp.constant.DatabaseConstant.DATABASE_TABLE
import com.example.taskmanagementapp.constant.EventInfo
import com.example.taskmanagementapp.constant.TaskType
import java.util.*

@Entity(tableName = DATABASE_TABLE)
data class Note(
    @PrimaryKey
    val date: Date,
    val listToDoTask: List<TaskType> = emptyList(),
    val listEvent: List<EventInfo> = emptyList()
    )