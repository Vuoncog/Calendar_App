package com.example.taskmanagementapp.constant

data class SubTask (val done : Boolean, val title : String){
    constructor() : this(false,"")
}