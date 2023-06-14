package com.example.taskmanagementapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.taskmanagementapp.constant.Graph
import com.example.taskmanagementapp.constant.GraphRoute
import com.example.taskmanagementapp.data.SharedViewModel
import com.example.taskmanagementapp.navigation.calendar.topCalendarNavigation
import com.example.taskmanagementapp.navigation.home.topHomeNavigation
import com.example.taskmanagementapp.navigation.management.topManagementNavigation
import com.example.taskmanagementapp.navigation.profile.topProfileNavigation
import kotlinx.coroutines.launch
import java.util.*

@Composable
fun TopNavGraph(
    navController: NavHostController,
    onPrevWeekClicked: (Date) -> Unit,
    onNextWeekClicked: (Date) -> Unit,
    date: Date,
    sharedViewModel: SharedViewModel,
    systemColor: Color
) {
    val coroutineScope = rememberCoroutineScope()
    val onBackClicked: () -> Unit = {
        coroutineScope.launch {
            navController.popBackStack()
        }
    }
    val onFinished: () -> Unit = {
        coroutineScope.launch {
            navController.navigate(GraphRoute.Calendar.route)
        }
    }

    NavHost(
        navController = navController,
        route = Graph.MAIN,
        startDestination = Graph.HOME
    ) {
        topHomeNavigation()

        topProfileNavigation()

        topManagementNavigation(
            navController = navController,
            sharedViewModel = sharedViewModel,
            date = date,
            onPrevWeekClicked = onPrevWeekClicked,
            onNextWeekClicked = onNextWeekClicked,
            onBackClicked = onBackClicked,
            onFinished = onFinished,
            systemColor = systemColor
        )

        topCalendarNavigation(
            sharedViewModel = sharedViewModel,
            date = date,
            onPrevWeekClicked = onPrevWeekClicked,
            onNextWeekClicked = onNextWeekClicked,
            onBackClicked = onBackClicked,
            onFinished = onFinished,
            systemColor = systemColor
        )
    }
}