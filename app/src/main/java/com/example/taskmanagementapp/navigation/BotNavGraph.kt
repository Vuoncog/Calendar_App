package com.example.taskmanagementapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.taskmanagementapp.constant.BottomBarItems
import com.example.taskmanagementapp.ui.screens.calendar.CalendarContent
import com.example.taskmanagementapp.ui.screens.home.HomeContent
import com.example.taskmanagementapp.ui.screens.memo.MemoContent
import com.example.taskmanagementapp.ui.screens.profile.ProfileContent

@Composable
fun BottomNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarItems.Home.route
    ) {
        composable(BottomBarItems.Home.route) {
            HomeContent()
        }
        composable(BottomBarItems.Calendar.route) {
            CalendarContent()
        }
        composable(BottomBarItems.Memo.route) {
            MemoContent()
        }
        composable(BottomBarItems.Profile.route) {
            ProfileContent()
        }

    }
}