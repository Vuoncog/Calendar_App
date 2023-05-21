package com.example.taskmanagementapp.constant

sealed class Screen(
    val route: String
) {
    object AddTask: Screen(
        route = "add_task"
    )
}