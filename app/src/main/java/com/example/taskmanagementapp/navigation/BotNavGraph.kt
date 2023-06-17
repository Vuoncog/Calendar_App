package com.example.taskmanagementapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.taskmanagementapp.constant.Graph
import com.example.taskmanagementapp.constant.SystemColorSet
import com.example.taskmanagementapp.data.SharedViewModel
import com.example.taskmanagementapp.navigation.calendar.calendarNavigation
import com.example.taskmanagementapp.navigation.home.homeNavigation
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
    onColorChange: (SystemColorSet) -> Unit
) {
    val resetCalendar = Calendar.getInstance()
    resetCalendar.time = selectedDate

    LaunchedEffect(key1 = true, block = {
        sharedViewModel.getEventInfo()
        sharedViewModel.getToDoTask()
    })

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
            systemColorSet = systemColorSet
        )

        calendarNavigation(
            navController = navController,
            sharedViewModel = sharedViewModel,
            systemColorSet = systemColorSet,
            currentDate = currentDate,
            calendar = calendar,
            selectedDate = selectedDate,
            onSelectDay = onSelectDay,
            isShowBottomBarItems = isShowBottomBarItems
        )

        managementNavigation(
            navController = navController,
            sharedViewModel = sharedViewModel,
            systemColorSet = systemColorSet,
            currentDate = currentDate,
            calendar = calendar,
            selectedDate = selectedDate,
            onSelectDay = onSelectDay,
            isShowBottomBarItems = isShowBottomBarItems
        )

        profileNavigation(
            sharedViewModel = sharedViewModel,
            isShowBottomBarItems = isShowBottomBarItems,
            systemColorSet = systemColorSet,
            onColorChange = onColorChange
        )
    }
}