package com.example.taskmanagementapp.constant

import com.google.firebase.database.PropertyName

data class ToDoTask(
     val taskType: TaskType? = TaskType.Running,
     val taskName: String,
     private var isDone: Boolean,
     val time: Long,
     val listSubTasks : List<SubTask> = emptyList()
){
    constructor() : this(null,"",false,0L)
    @PropertyName("done")
    fun getDone() : Boolean = isDone
    @PropertyName("done")
    fun setDone(mDone : Boolean) {
        isDone = mDone
    }
}