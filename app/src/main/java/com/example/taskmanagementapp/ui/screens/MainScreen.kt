package com.example.taskmanagementapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.taskmanagementapp.ViewModel.LogInViewModel
import com.example.taskmanagementapp.navigation.BottomNavGraph
import com.example.taskmanagementapp.navigation.TopNavGraph
import com.example.taskmanagementapp.ui.component.BottomBar
import java.util.*


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(logInViewModel: LogInViewModel? = null) {
    val calendar = Calendar.getInstance(Locale.getDefault())
    calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
    var currentDate by rememberSaveable { mutableStateOf(calendar.time) }

    val navController = rememberNavController()
    var todayDate by remember { mutableStateOf(Calendar.getInstance(Locale.getDefault()).time) }

    Scaffold(
        backgroundColor = Color.White,
        topBar = {
            TopNavGraph(
                navController = navController,
                onPrevWeekClicked = {
                    calendar.time = it
                    currentDate = it
                },
                onNextWeekClicked = {
                    calendar.time = it
                    currentDate = it
                },
                date = currentDate
            )
        },
        bottomBar = {
            BottomBar(
                navController = navController,
                resetDay = { day ->
                    calendar.time = day
                    calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
                    currentDate = calendar.time
                    todayDate = day
                }
            )
        }
    ) {
        BottomNavGraph(
            navController = navController,
            logInViewModel = logInViewModel,
            onExpandIconClicked = {

            },
            date = currentDate,
            calendar = calendar,
            selectedDate = todayDate,
            onSelectDay = { day ->
                calendar.time = day
                todayDate = day
            },
        )
    }
}

@Composable
@Preview
fun Preview() {
    MainScreen()
}