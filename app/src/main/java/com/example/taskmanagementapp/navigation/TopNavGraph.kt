package com.example.taskmanagementapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.taskmanagementapp.constant.BottomBarItems
import com.example.taskmanagementapp.ui.screens.calendar.CalendarTopAppBar
import com.example.taskmanagementapp.ui.screens.home.HomeAppBar
import com.example.taskmanagementapp.ui.screens.memo.MemoTopAppBar
import com.example.taskmanagementapp.ui.screens.profile.ProfileTopAppBar

@Composable
fun TopNavGraph(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = BottomBarItems.Home.route){
        composable(BottomBarItems.Home.route){
            HomeAppBar()
        }
        composable(BottomBarItems.Calendar.route){
            CalendarTopAppBar()
        }
        composable(BottomBarItems.Profile.route){
            ProfileTopAppBar()
        }
        composable(BottomBarItems.Memo.route){
            MemoTopAppBar()
        }

    }
}