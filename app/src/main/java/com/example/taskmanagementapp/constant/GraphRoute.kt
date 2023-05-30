package com.example.taskmanagementapp.constant

import androidx.annotation.DrawableRes
import com.example.taskmanagementapp.R

sealed class GraphRoute(
    val title: String,
    val route: String,
    @DrawableRes val icon: Int,
    @DrawableRes val iconSelected: Int,
) {

    object Home: GraphRoute(
        title = "Home",
        route = "home",
        icon = R.drawable.ic_home,
        iconSelected = R.drawable.ic_home_solid
    )
    object Calendar: GraphRoute(
        title = "Calendar",
        route = "calendar",
        icon = R.drawable.ic_calendar,
        iconSelected = R.drawable.ic_calendar_solid
    )
    object Management: GraphRoute(
        title = "Memo",
        route = "management",
        icon = R.drawable.ic_notepad,
        iconSelected = R.drawable.ic_notepad_solid,
    )
    object Profile: GraphRoute(
        title = "Profile",
        route = "profile",
        icon = R.drawable.ic_person,
        iconSelected = R.drawable.ic_person_solid
    )
}