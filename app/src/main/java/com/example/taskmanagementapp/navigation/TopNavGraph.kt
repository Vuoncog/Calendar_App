package com.example.taskmanagementapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.taskmanagementapp.constant.BottomBarItems
import com.example.taskmanagementapp.ui.screens.calendar.CalendarTopAppBar
import com.example.taskmanagementapp.ui.screens.home.HomeAppBar
import com.example.taskmanagementapp.ui.screens.management.ManagementTopAppBar
import com.example.taskmanagementapp.ui.screens.profile.ProfileTopAppBar
import java.time.LocalDate

@Composable
fun TopNavGraph(
    navController: NavHostController,
    currentDay: LocalDate,
    previousDayClicked: (LocalDate) -> Unit,
    nextDayClicked: (LocalDate) -> Unit,
) {
    NavHost(navController = navController, startDestination = BottomBarItems.Home.route) {
        composable(BottomBarItems.Home.route) {
            HomeAppBar()
        }
        composable(BottomBarItems.Calendar.route) {
            CalendarTopAppBar()
        }
        composable(BottomBarItems.Management.route) {
            ManagementTopAppBar(
                currentDay = currentDay,
                previousDayClicked = previousDayClicked,
                nextDayClicked = nextDayClicked
            )
        }
        composable(BottomBarItems.Profile.route) {
            ProfileTopAppBar()
        }

    }
}