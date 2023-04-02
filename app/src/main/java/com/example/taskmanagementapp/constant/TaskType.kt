package com.example.taskmanagementapp.constant

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.example.taskmanagementapp.R
import com.example.taskmanagementapp.ui.theme.SystemColor

sealed class TaskType(
    @DrawableRes val icon: Int,
    val description: String,
    val color: Color = SystemColor
) {
    object Running: TaskType(
        icon = R.drawable.ic_running_man,
        description = "Running"
    )
    object PetFood: TaskType(
        icon = R.drawable.ic_pet,
        description = "Food the pet"
    )
    object WalkTheDog: TaskType(
        icon = R.drawable.ic_pet_walking,
        description = "Walk the dog"
    )
    object Shopping: TaskType(
        icon = R.drawable.ic_bag,
        description = "Shopping"
    )
}