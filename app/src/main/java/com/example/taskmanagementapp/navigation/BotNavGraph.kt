package com.example.taskmanagementapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.taskmanagementapp.constant.Graph
import com.example.taskmanagementapp.constant.SystemColorSet
import com.example.taskmanagementapp.data.SharedViewModel
import com.example.taskmanagementapp.navigation.calendar.calendarNavigation
import com.example.taskmanagementapp.navigation.home.homeNavigation
import com.example.taskmanagementapp.navigation.management.managementNavigation
import com.example.taskmanagementapp.navigation.profile.profileNavigation
import java.util.*



@Composable
fun BottomNavGraph(
    navController: NavHostController,
    sharedViewModel: SharedViewModel,
    systemColorSet: SystemColorSet,
    currentDate: Date,
    calendar: Calendar,
    selectedDate: Date,
    onSelectDay: (Date) -> Unit,
    isShowBottomBarItems: (Boolean) -> Unit,
    onColorChange: (SystemColorSet) -> Unit,
    onResetDay : (date : Date) -> Unit
) {
    val resetCalendar = Calendar.getInstance()
    resetCalendar.time = selectedDate

    NavHost(
        navController = navController,
        route = Graph.MAIN,
        startDestination = Graph.HOME
    ) {
        homeNavigation(
            allTasksInDate = sharedViewModel.listTaskResult,
            allEventsInDate = sharedViewModel.listEventResult,
            currentDate = currentDate,
            isShowBottomBarItems = isShowBottomBarItems,
            systemColorSet = systemColorSet,
            sharedViewModel = sharedViewModel
        )

        calendarNavigation(
            navController = navController,
            sharedViewModel = sharedViewModel,
            systemColorSet = systemColorSet,
            currentDate = currentDate,
            calendar = calendar,
            selectedDate = selectedDate,
            onSelectDay = onSelectDay,
            isShowBottomBarItems = isShowBottomBarItems,
            onResetDay = onResetDay
        )

        managementNavigation(
            navController = navController,
            sharedViewModel = sharedViewModel,
            systemColorSet = systemColorSet,
            currentDate = currentDate,
            calendar = calendar,
            selectedDate = selectedDate,
            onSelectDay = onSelectDay,
            isShowBottomBarItems = isShowBottomBarItems,
            onResetDay = onResetDay
        )

        profileNavigation(
            sharedViewModel = sharedViewModel,
            isShowBottomBarItems = isShowBottomBarItems,
            systemColorSet = systemColorSet,
            onColorChange = onColorChange
        )
    }
}