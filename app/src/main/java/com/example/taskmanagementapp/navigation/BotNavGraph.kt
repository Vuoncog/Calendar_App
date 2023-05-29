package com.example.taskmanagementapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.taskmanagementapp.constant.BottomBarItems
import com.example.taskmanagementapp.constant.Screen
import com.example.taskmanagementapp.data.SharedViewModel
import com.example.taskmanagementapp.ui.screens.calendar.CalendarContent
import com.example.taskmanagementapp.ui.screens.home.HomeContent
import com.example.taskmanagementapp.ui.screens.management.ManagementContent
import com.example.taskmanagementapp.ui.screens.management.task.AddTask
import com.example.taskmanagementapp.ui.screens.profile.ProfileContent
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
        startDestination = BottomBarItems.Home.route
    ) {
        composable(BottomBarItems.Home.route) {
            isShowBottomBarItems(true)
            HomeContent(
                listAllTask = allTaskInDate,
                currentDate = Calendar.getInstance().time
            )
        }
        composable(BottomBarItems.Calendar.route) {
            isShowBottomBarItems(true)
            CalendarContent(
                date = currentDate,
                calendar = calendar,
                selectedDate = selectedDate,
                onSelectDay = onSelectDay,
                navigateToAddTask = {
                    navController.navigate("${BottomBarItems.Management.route}/${Screen.AddTask.route}")
                },
                sharedViewModel = sharedViewModel
            )
        }
        composable(BottomBarItems.Management.route) {
            isShowBottomBarItems(true)
            ManagementContent(
                date = currentDate,
                calendar = calendar,
                selectedDate = selectedDate,
                onSelectDay = onSelectDay,
                navigateToAddTask = {
                    navController.navigate("${BottomBarItems.Management.route}/${Screen.AddTask.route}")
                },
                sharedViewModel = sharedViewModel
            )
        }
        composable(BottomBarItems.Profile.route) {
            isShowBottomBarItems(true)
            ProfileContent(sharedViewModel = sharedViewModel)
        }

        composable("${BottomBarItems.Management.route}/${Screen.AddTask.route}") {
            isShowBottomBarItems(false)
            AddTask(sharedViewModel)
        }
        composable("${BottomBarItems.Calendar.route}/${Screen.AddTask.route}") {
            isShowBottomBarItems(false)
            AddTask(sharedViewModel)
        }
    }
}