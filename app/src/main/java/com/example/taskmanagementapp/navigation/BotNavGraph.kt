package com.example.taskmanagementapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.taskmanagementapp.constant.Graph
import com.example.taskmanagementapp.data.SharedViewModel
import com.example.taskmanagementapp.navigation.calendar.calendarNavigation
import com.example.taskmanagementapp.navigation.home.homeNavigation
import com.example.taskmanagementapp.navigation.profile.profileNavigation
import java.util.*



@Composable
fun BottomNavGraph(
    navController: NavHostController,
    sharedViewModel: SharedViewModel,
    currentDate: Date,
    calendar: Calendar,
    selectedDate: Date,
    onSelectDay: (Date) -> Unit,
    isShowBottomBarItems: (Boolean) -> Unit
) {
    val resetCalendar = Calendar.getInstance()
    resetCalendar.time = selectedDate

    LaunchedEffect(key1 = true, block = {
        sharedViewModel.getSelectedDate(currentDate)
    })

    val allTaskInDate by sharedViewModel.allTaskInDate.collectAsState()

    NavHost(
        navController = navController,
        route = Graph.MAIN,
        startDestination = Graph.HOME
    ) {
        homeNavigation(
            allTaskInDate = allTaskInDate,
            currentDate = currentDate,
            isShowBottomBarItems = isShowBottomBarItems
        )

        calendarNavigation(
            navController = navController,
            sharedViewModel = sharedViewModel,
            currentDate = currentDate,
            calendar = calendar,
            selectedDate = selectedDate,
            onSelectDay = onSelectDay,
            isShowBottomBarItems = isShowBottomBarItems
        )

        managementNavigation(
            navController = navController,
            sharedViewModel = sharedViewModel,
            currentDate = currentDate,
            calendar = calendar,
            selectedDate = selectedDate,
            onSelectDay = onSelectDay,
            isShowBottomBarItems = isShowBottomBarItems
        )

        profileNavigation(
            sharedViewModel = sharedViewModel,
            isShowBottomBarItems = isShowBottomBarItems
        )
    }
}