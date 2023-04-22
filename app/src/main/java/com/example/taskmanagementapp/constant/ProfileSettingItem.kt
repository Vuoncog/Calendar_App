package com.example.taskmanagementapp.constant

import androidx.annotation.DrawableRes
import com.example.taskmanagementapp.R

sealed class ProfileSettingItem(
    @DrawableRes val icon: Int,
    val title: String,
    val stateSettingItem: StateSettingItem,
    val area: ProfileAreaSettingName,
    val route: String
) {
    object NotificationAndAlerts: ProfileSettingItem(
        icon = R.drawable.ic_alerts,
        title = "Notification and Alerts",
        stateSettingItem = StateSettingItem.SWITCH,
        area = ProfileAreaSettingName.GENERAL,
        route = "notification"
    )
    object Color: ProfileSettingItem(
        icon = R.drawable.ic_paint_board,
        title = "Color",
        stateSettingItem = StateSettingItem.COLOR,
        area = ProfileAreaSettingName.GENERAL,
        route = "color"
    )
    object Settings: ProfileSettingItem(
        icon = R.drawable.ic_settings,
        title = "Settings",
        stateSettingItem = StateSettingItem.NONE,
        area = ProfileAreaSettingName.GENERAL,
        route = "settings"
    )
    object MoreInformation: ProfileSettingItem(
        icon = R.drawable.ic_detail,
        title = "More information",
        stateSettingItem = StateSettingItem.NONE,
        area = ProfileAreaSettingName.ACCOUNT,
        route = "more_information"
    )
    object Logout: ProfileSettingItem(
        icon = R.drawable.ic_logout,
        title = "Log Out",
        stateSettingItem = StateSettingItem.NONE,
        area = ProfileAreaSettingName.ACCOUNT,
        route = "logout"
    )

}