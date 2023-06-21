package com.example.taskmanagementapp.constant

import com.google.firebase.database.PropertyName

data class ToDoTask(
    val taskType: TaskType = TaskType(0,""),
    val taskName: String,
    private var isDone: Boolean,
    val time: Long,
    val listSubTasks : List<SubTask> = emptyList()
){
    constructor() : this(TaskType(0,""),"",false,0L)
    @PropertyName("done")
    fun getDone() : Boolean = isDone
    @PropertyName("done")
    fun setDone(mDone : Boolean) {
        isDone = mDone
    }
}