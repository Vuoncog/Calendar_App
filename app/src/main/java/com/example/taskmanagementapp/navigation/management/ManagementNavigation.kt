package com.example.taskmanagementapp.navigation

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.taskmanagementapp.constant.*
import com.example.taskmanagementapp.data.SharedViewModel
import com.example.taskmanagementapp.ui.screens.management.ManagementContent
import com.example.taskmanagementapp.ui.screens.task.AddTask
import com.google.gson.Gson
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
        val navigateToUpdateTask : (toDoTask : ToDoTask) -> Unit = {
            val taskString = Gson().toJson(it)
            navController.navigate("${SubScreen.TaskDetail.route}/${taskString}")

        }
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
                },
                navigateToUpdateTask = navigateToUpdateTask
            )
        }

        composable(route = SubScreen.AddTodoTask.route) {
            isShowBottomBarItems(false)
            AddTask(
                sharedViewModel = sharedViewModel,
                systemColorSet = systemColorSet,
                isToDoTask = true
            )
        }
        composable(
            route = "${SubScreen.TaskDetail.route}/{task}",
            arguments = listOf(navArgument("task") { type = NavType.StringType})
        ){
            isShowBottomBarItems(false)
            AddTask(
                sharedViewModel = sharedViewModel,
                systemColorSet = systemColorSet,
                taskInfo = it.arguments?.getString("task"),
                isToDoTask = true
            )
        }
    }
}