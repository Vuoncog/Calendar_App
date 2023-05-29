package com.example.taskmanagementapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.taskmanagementapp.constant.BottomBarItems
import com.example.taskmanagementapp.constant.Screen
import com.example.taskmanagementapp.data.SharedViewModel
import com.example.taskmanagementapp.ui.screens.calendar.CalendarTopAppBar
import com.example.taskmanagementapp.ui.screens.home.HomeAppBar
import com.example.taskmanagementapp.ui.screens.management.ManagementTopAppBar
import com.example.taskmanagementapp.ui.screens.management.task.AddTaskTopAppBar
import com.example.taskmanagementapp.ui.screens.profile.ProfileTopAppBar
import kotlinx.coroutines.launch
import java.util.*

@Composable
fun TopNavGraph(
    navController: NavHostController,
    onPrevWeekClicked: (Date) -> Unit,
    onNextWeekClicked: (Date) -> Unit,
    date: Date,
    sharedViewModel: SharedViewModel
) {
    val coroutineScope = rememberCoroutineScope()
    val onBackClicked: () -> Unit = {
        coroutineScope.launch {
            navController.popBackStack()
        }
    }
    val onFinished : () -> Unit = {
        coroutineScope.launch {
            navController.navigate(BottomBarItems.Calendar.route)
        }
    }

    NavHost(navController = navController, startDestination = BottomBarItems.Home.route) {
        composable(BottomBarItems.Home.route) {
            HomeAppBar()
        }
        composable(BottomBarItems.Calendar.route) {
            CalendarTopAppBar(
                onNextWeekClicked = onNextWeekClicked,
                onPrevWeekClicked = onPrevWeekClicked,
                currentDay = date,
            )
        }
        composable(BottomBarItems.Management.route) {
            ManagementTopAppBar(
                onNextWeekClicked = onNextWeekClicked,
                onPrevWeekClicked = onPrevWeekClicked,
                currentDay = date,
            )
        }
        composable(BottomBarItems.Profile.route) {
            ProfileTopAppBar()
        }

        composable("${BottomBarItems.Management.route}/${Screen.AddTask.route}") {
            AddTaskTopAppBar(
                onBackClicked = onBackClicked,
                sharedViewModel = sharedViewModel,
                onFinished = onFinished
            )
        }
        composable("${BottomBarItems.Calendar.route}/${Screen.AddTask.route}") {
            AddTaskTopAppBar(
                onBackClicked = onBackClicked,
                sharedViewModel = sharedViewModel,
                onFinished = onFinished
            )
        }

    }
}