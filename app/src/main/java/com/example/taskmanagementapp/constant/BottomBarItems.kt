package com.example.taskmanagementapp.constant

import androidx.annotation.DrawableRes
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.taskmanagementapp.R

sealed class BottomBarItems(
    val title: String,
    val route: String,
    @DrawableRes val icon: Int
) {

    object Home: BottomBarItems(
        title = "Home",
        route = "home",
        icon = R.drawable.ic_home
    )
    object Calendar: BottomBarItems(
        title = "Calendar",
        route = "calendar",
        icon = R.drawable.ic_home_solid
    )
    object Memo: BottomBarItems(
        title = "Memo",
        route = "memo",
        icon = R.drawable.ic_notepad
    )
    object Profile: BottomBarItems(
        title = "Profile",
        route = "profile",
        icon = R.drawable.ic_person
    )
}