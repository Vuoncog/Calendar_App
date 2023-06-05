package com.example.taskmanagementapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.example.taskmanagementapp.constant.SystemColorSet
import com.example.taskmanagementapp.data.SharedViewModel
import com.example.taskmanagementapp.navigation.BottomNavGraph
import com.example.taskmanagementapp.navigation.TopNavGraph
import com.example.taskmanagementapp.ui.component.BottomBar
import java.util.*


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(
    sharedViewModel: SharedViewModel,
    systemColorSet: SystemColorSet,
    systemColorSetChange: (SystemColorSet) -> Unit,
) {
    val calendar = Calendar.getInstance(Locale.getDefault())
    calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
    var currentDate by rememberSaveable { mutableStateOf(calendar.time) }

    val navController = rememberNavController()
    var todayDate by remember { mutableStateOf(Calendar.getInstance(Locale.getDefault()).time) }
    var showBottomBar by remember { mutableStateOf(true) }

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
                date = currentDate,
                sharedViewModel = sharedViewModel
            )
        },
        bottomBar = {
            if(showBottomBar){
                BottomBar(
                    navController = navController,
                    systemColor = systemColorSet.primaryColor,
                    resetDay = { day ->
                        calendar.time = day
                        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
                        currentDate = calendar.time
                        todayDate = day
                    }
                )
            }
        },
    ) {
        BottomNavGraph(
            navController = navController,
            sharedViewModel = sharedViewModel,
            currentDate = currentDate,
            calendar = calendar,
            selectedDate = todayDate,
            onSelectDay = { day ->
                calendar.time = day
                todayDate = day
            },
            isShowBottomBarItems = {
                showBottomBar = it
            },
            systemColorSet = systemColorSet,
            onColorChange = systemColorSetChange
        )
    }
}