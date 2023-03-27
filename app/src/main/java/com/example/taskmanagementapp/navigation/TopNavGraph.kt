package com.example.taskmanagementapp.navigation

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.taskmanagementapp.constant.BottomBarItems
import com.example.taskmanagementapp.ui.screens.calendar.CalendarAppBar
import com.example.taskmanagementapp.ui.screens.calendar.CalendarTopAppBar
import com.example.taskmanagementapp.ui.component.BottomBar
import com.example.taskmanagementapp.ui.screens.home.HomeAppBar
import com.example.taskmanagementapp.ui.screens.home.HomeContent
import com.example.taskmanagementapp.ui.screens.home.HomeScreen
import com.example.taskmanagementapp.ui.login.Login
import com.example.taskmanagementapp.ui.login.LoginContent
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
//            TopAppBar(
//                contentColor = contentColorFor(backgroundColor = Color.Transparent),
//                elevation = 0.dp,
//                backgroundColor = Color.Transparent,
//            ) {
//
//            }
        }

    }
}