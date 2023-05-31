package com.example.taskmanagementapp.navigation.calendar

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.taskmanagementapp.constant.Graph
import com.example.taskmanagementapp.constant.GraphRoute
import com.example.taskmanagementapp.constant.SubScreen
import com.example.taskmanagementapp.data.SharedViewModel
import com.example.taskmanagementapp.ui.screens.calendar.CalendarTopAppBar
import com.example.taskmanagementapp.ui.screens.management.task.AddTaskTopAppBar
import java.util.*

fun NavGraphBuilder.topCalendarNavigation(
    navController: NavHostController,
    sharedViewModel: SharedViewModel,
    date: Date,
    onPrevWeekClicked: (Date) -> Unit,
    onNextWeekClicked: (Date) -> Unit,
    onBackClicked: () -> Unit,
    onFinished: () -> Unit,
){
    navigation(
        startDestination = GraphRoute.Calendar.route,
        route = Graph.CALENDAR
    ){
        composable(route = GraphRoute.Calendar.route){
            CalendarTopAppBar(
                onNextWeekClicked = onNextWeekClicked,
                onPrevWeekClicked = onPrevWeekClicked,
                currentDay = date,
            )
        }
        composable(route = SubScreen.AddEvent.route){
            AddTaskTopAppBar(
                onBackClicked = onBackClicked,
                sharedViewModel = sharedViewModel,
                onFinished = onFinished
            )
        }
    }
}