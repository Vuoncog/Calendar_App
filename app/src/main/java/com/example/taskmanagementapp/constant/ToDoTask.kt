package com.example.taskmanagementapp.constant

data class ToDoTask(
    val taskType: TaskType,
    val taskName: String,
    val isDone: Boolean,
)