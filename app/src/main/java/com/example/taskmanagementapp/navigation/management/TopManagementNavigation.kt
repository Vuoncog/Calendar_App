package com.example.taskmanagementapp.navigation.management

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.taskmanagementapp.constant.Graph
import com.example.taskmanagementapp.constant.GraphRoute
import com.example.taskmanagementapp.constant.SubScreen
import com.example.taskmanagementapp.data.SharedViewModel
import com.example.taskmanagementapp.ui.screens.management.ManagementTopAppBar
import com.example.taskmanagementapp.ui.screens.task.AddTaskTopAppBar
import java.util.*

fun NavGraphBuilder.topManagementNavigation(
    navController: NavHostController,
    sharedViewModel: SharedViewModel,
    date: Date,
    onPrevWeekClicked: (Date) -> Unit,
    onNextWeekClicked: (Date) -> Unit,
    onBackClicked: () -> Unit,
    onFinished: () -> Unit,
){
    navigation(
        startDestination = GraphRoute.Management.route,
        route = Graph.MANAGEMENT
    ){
        composable(route = GraphRoute.Management.route){
            ManagementTopAppBar(
                onNextWeekClicked = onNextWeekClicked,
                onPrevWeekClicked = onPrevWeekClicked,
                currentDay = date,
            )
        }
        composable(route = SubScreen.AddTodoTask.route){
            AddTaskTopAppBar(
                onBackClicked = onBackClicked,
                sharedViewModel = sharedViewModel,
                onFinished = onFinished,
                isEvent = false
            )
        }
    }
}