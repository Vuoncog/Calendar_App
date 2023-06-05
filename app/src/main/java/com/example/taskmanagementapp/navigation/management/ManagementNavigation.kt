package com.example.taskmanagementapp.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.taskmanagementapp.constant.Graph
import com.example.taskmanagementapp.constant.GraphRoute
import com.example.taskmanagementapp.constant.SubScreen
import com.example.taskmanagementapp.constant.SystemColorSet
import com.example.taskmanagementapp.data.SharedViewModel
import com.example.taskmanagementapp.ui.screens.management.ManagementContent
import com.example.taskmanagementapp.ui.screens.task.AddTask
import java.util.*

fun NavGraphBuilder.managementNavigation(
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
        startDestination = GraphRoute.Management.route,
        route = Graph.MANAGEMENT
    ) {
        composable(route = GraphRoute.Management.route) {
            isShowBottomBarItems(true)
            ManagementContent(
                systemColorSet = systemColorSet,
                date = currentDate,
                calendar = calendar,
                selectedDate = selectedDate,
                onSelectDay = onSelectDay,
                sharedViewModel = sharedViewModel,
                navigateToAddTask = {
                    navController.navigate(route = SubScreen.AddTodoTask.route)
                }
            )
        }

        composable(route = SubScreen.AddTodoTask.route) {
            isShowBottomBarItems(false)
            AddTask(
                sharedViewModel = sharedViewModel,
                systemColorSet = systemColorSet
            )
        }
    }
}