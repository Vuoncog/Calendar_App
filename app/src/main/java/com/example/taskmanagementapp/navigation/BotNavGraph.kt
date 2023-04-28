package com.example.taskmanagementapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.taskmanagementapp.ViewModel.LogInViewModel
import com.example.taskmanagementapp.constant.BottomBarItems
import com.example.taskmanagementapp.data.SharedViewModel
import com.example.taskmanagementapp.ui.screens.calendar.CalendarContent
import com.example.taskmanagementapp.ui.screens.home.HomeContent
import com.example.taskmanagementapp.ui.screens.management.ManagementContent
import com.example.taskmanagementapp.ui.screens.profile.ProfileContent
import java.util.*



@Composable
fun BottomNavGraph(
    navController: NavHostController,
    sharedViewModel: SharedViewModel,
    logInViewModel: LogInViewModel? = null,
    date: Date,
    calendar: Calendar,
    selectedDate: Date,
    onSelectDay: (Date) -> Unit,
) {
    val resetCalendar = Calendar.getInstance()
    resetCalendar.time = selectedDate

    LaunchedEffect(key1 = true, block = {
        sharedViewModel.getAllTask()
    })

    val allTaskInDate by sharedViewModel.allTaskInDate.collectAsState()

    NavHost(
        navController = navController,
        startDestination = BottomBarItems.Home.route
    ) {
        composable(BottomBarItems.Home.route) {
            HomeContent(
                listAllTask = allTaskInDate,
                currentDate = Calendar.getInstance().time
            )
        }
        composable(BottomBarItems.Calendar.route) {
            CalendarContent(
                date = date,
                calendar = calendar,
                selectedDate = selectedDate,
                onSelectDay = onSelectDay
            )
        }
        composable(BottomBarItems.Management.route) {
            ManagementContent(
                date = date,
                calendar = calendar,
                selectedDate = selectedDate,
                onSelectDay = onSelectDay
            )
        }
        composable(BottomBarItems.Profile.route) {
            ProfileContent(logInViewModel = logInViewModel)
        }
    }
}