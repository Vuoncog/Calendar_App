package com.example.taskmanagementapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.taskmanagementapp.ViewModel.LogInViewModel
import com.example.taskmanagementapp.navigation.BottomNavGraph
import com.example.taskmanagementapp.navigation.TopNavGraph
import com.example.taskmanagementapp.ui.component.BottomBar
import java.time.LocalDate

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(logInViewModel: LogInViewModel? = null) {
    val navController = rememberNavController()
    var currentDay by remember { mutableStateOf(LocalDate.now()) }

    Scaffold(
        backgroundColor = Color.White,
        topBar = {
            TopNavGraph(
                navController = navController,
                currentDay = currentDay,
                previousDayClicked = { selectedDay ->
                    currentDay = selectedDay.minusDays(1)
                },
                nextDayClicked = { selectedDay ->
                    currentDay = selectedDay.plusDays(1)
                }
            )
        },
        bottomBar = {
            BottomBar(navController = navController)
        }
    ) {
        BottomNavGraph(
            navController = navController,
            currentDay = currentDay,
            calendarDaySelected = { selectedDay ->
                currentDay = selectedDay
            },
            logInViewModel = logInViewModel,
            onExpandIconClicked = {

            }
        )
    }
}

@Composable
@Preview
fun Preview() {
    MainScreen()
}