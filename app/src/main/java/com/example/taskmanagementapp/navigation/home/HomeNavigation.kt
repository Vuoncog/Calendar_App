package com.example.taskmanagementapp.navigation.home

import android.util.Log
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.taskmanagementapp.constant.*
import com.example.taskmanagementapp.ui.screens.home.HomeContent
import java.util.*

fun NavGraphBuilder.homeNavigation(
    allTasksInDate: List<ToDoTask>,
    allEventsInDate: List<EventInfo>,
    currentDate: Date,
    isShowBottomBarItems: (Boolean) -> Unit,
    systemColorSet: SystemColorSet
){
    navigation(
        startDestination = GraphRoute.Home.route,
        route = Graph.HOME
    ){
        composable(route = GraphRoute.Home.route){
            isShowBottomBarItems(true)
            HomeContent(
                listAllTask = allTasksInDate,
                currentDate = currentDate,
                systemColorSet = systemColorSet,
                listAllEvent = allEventsInDate
            )
        }

    }
}