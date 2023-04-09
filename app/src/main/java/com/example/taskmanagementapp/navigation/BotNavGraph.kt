package com.example.taskmanagementapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.taskmanagementapp.ViewModel.LogInViewModel
import com.example.taskmanagementapp.constant.BottomBarItems
import com.example.taskmanagementapp.constant.ProfileSettingItem
import com.example.taskmanagementapp.ui.screens.calendar.CalendarContent
import com.example.taskmanagementapp.ui.screens.home.HomeContent
import com.example.taskmanagementapp.ui.screens.management.ManagementContent
import com.example.taskmanagementapp.ui.screens.profile.ProfileContent
import java.time.LocalDate

@Composable
fun BottomNavGraph(
    navController: NavHostController,
    currentDay: LocalDate,
    calendarDaySelected: (LocalDate) -> Unit,
    logInViewModel: LogInViewModel? = null,
    onExpandIconClicked: (ProfileSettingItem) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarItems.Home.route
    ) {
        composable(BottomBarItems.Home.route) {
            HomeContent(logInViewModel = logInViewModel)
        }
        composable(BottomBarItems.Calendar.route) {
            CalendarContent(
                currentDay = currentDay,
                calendarDaySelected = calendarDaySelected
            )
        }
        composable(BottomBarItems.Management.route) {
            ManagementContent(
                currentDay = currentDay,
                calendarDaySelected = calendarDaySelected
            )
        }
        composable(BottomBarItems.Profile.route) {
            ProfileContent(onExpandIconClicked = onExpandIconClicked)
        }

    }
}