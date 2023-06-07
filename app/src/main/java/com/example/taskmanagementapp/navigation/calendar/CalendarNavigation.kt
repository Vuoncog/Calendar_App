package com.example.taskmanagementapp.navigation.calendar

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.taskmanagementapp.constant.Graph
import com.example.taskmanagementapp.constant.GraphRoute
import com.example.taskmanagementapp.constant.SubScreen
import com.example.taskmanagementapp.constant.SystemColorSet
import com.example.taskmanagementapp.data.SharedViewModel
import com.example.taskmanagementapp.ui.screens.calendar.CalendarContent
import com.example.taskmanagementapp.ui.screens.task.AddTask
import java.util.*

fun NavGraphBuilder.calendarNavigation(
    navController: NavHostController,
    sharedViewModel: SharedViewModel,
    systemColorSet: SystemColorSet,
    currentDate: Date,
    calendar: Calendar,
    selectedDate: Date,
    onSelectDay: (Date) -> Unit,
    isShowBottomBarItems: (Boolean) -> Unit
) {
    navigation(
        startDestination = GraphRoute.Calendar.route,
        route = Graph.CALENDAR
    ) {
        composable(route = GraphRoute.Calendar.route) {
            isShowBottomBarItems(true)
            CalendarContent(
                systemColor = systemColorSet.primaryColor,
                date = currentDate,
                calendar = calendar,
                selectedDate = selectedDate,
                onSelectDay = onSelectDay,
                navigateToAddTask = {
                    navController.navigate(SubScreen.AddEvent.route)
                },
                sharedViewModel = sharedViewModel,
                navController = navController
            )
        }

        composable(route = SubScreen.AddEvent.route) {
            isShowBottomBarItems(false)
            AddTask(
                sharedViewModel = sharedViewModel,
                systemColorSet = systemColorSet
            )
        }

        composable(
            route = "${SubScreen.EventDetail.route}/{event}",
            arguments = listOf(navArgument("event") { type = NavType.StringType})
        ){
            isShowBottomBarItems(false)
            AddTask(sharedViewModel = sharedViewModel, systemColorSet = systemColorSet, eventInfo = it.arguments?.getString("event"))
        }
    }
}