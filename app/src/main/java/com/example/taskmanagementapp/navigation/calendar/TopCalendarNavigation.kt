package com.example.taskmanagementapp.navigation.calendar

import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
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
    systemColor: Color,
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
                systemColor = systemColor
            )
        }
        composable(route = SubScreen.AddEvent.route){
            AddTaskTopAppBar(
                onBackClicked = onBackClicked,
                sharedViewModel = sharedViewModel,
                onFinished = onFinished,
                isUpdateEvent = false,
                isEvent = true,
                systemColor = systemColor
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
                isEvent = true,
                systemColor = systemColor
            )
        }
    }
}