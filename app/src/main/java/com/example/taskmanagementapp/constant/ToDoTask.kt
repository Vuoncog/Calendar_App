package com.example.taskmanagementapp.constant

import com.example.taskmanagementapp.R
import com.google.firebase.database.PropertyName

data class ToDoTask(
    val taskType: TaskType = TaskType(R.drawable.ic_running_man,""),
    val taskName: String,
    private var isDone: Boolean,
    val time: Long,
    val listSubTasks : List<SubTask> = emptyList()
){
    constructor() : this(TaskType(R.drawable.ic_running_man,""),"",false,0L)
    @PropertyName("done")
    fun getDone() : Boolean = isDone
    @PropertyName("done")
    fun setDone(mDone : Boolean) {
        isDone = mDone
    }
}