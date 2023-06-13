package com.example.taskmanagementapp.constant

sealed class SubScreen(val route: String) {
    object AddTodoTask : SubScreen(route = "management/addTodoTask")
    object TaskDetail : SubScreen(route = "management/todoInfo")
    object AddEvent : SubScreen(route = "calendar/addEvent")
    object EventDetail : SubScreen(route = "calendar/eventInfo")
}

