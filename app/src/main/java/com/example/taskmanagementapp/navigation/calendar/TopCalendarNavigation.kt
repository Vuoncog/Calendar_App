package com.example.taskmanagementapp.navigation.calendar

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.taskmanagementapp.constant.Graph
import com.example.taskmanagementapp.constant.GraphRoute
import com.example.taskmanagementapp.constant.SubScreen
import com.example.taskmanagementapp.data.SharedViewModel
import com.example.taskmanagementapp.ui.screens.calendar.CalendarTopAppBar
import com.example.taskmanagementapp.ui.screens.task.AddTaskTopAppBar
import java.util.*

fun NavGraphBuilder.topCalendarNavigation(
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
                onFinished = onFinished,
                isUpdateEvent = false,
                isEvent = true
            )
        }
        composable(
            route = "${SubScreen.EventDetail.route}/{event}",
            arguments = listOf(navArgument("event") { type = NavType.StringType})
        ){
            AddTaskTopAppBar(
                onBackClicked = onBackClicked,
                sharedViewModel = sharedViewModel,
                onFinished = onFinished,
                isUpdateEvent = true,
                isEvent = true
            )
        }
    }
}