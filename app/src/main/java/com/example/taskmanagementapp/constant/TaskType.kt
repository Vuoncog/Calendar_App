package com.example.taskmanagementapp.constant

import androidx.annotation.DrawableRes

data class TaskType(
    @DrawableRes val icon: Int = 0,
    val description: String,
) {
    /*object Running: TaskType(
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
    )*/
    constructor() : this(0,"")
}