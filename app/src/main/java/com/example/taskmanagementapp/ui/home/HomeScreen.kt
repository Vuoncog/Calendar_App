package com.example.taskmanagementapp.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.taskmanagementapp.navigation.BottomNavGraph
import com.example.taskmanagementapp.navigation.TopNavGraph
import com.example.taskmanagementapp.ui.component.BottomBar

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen() {
    val navController = rememberNavController()

    Scaffold(
        backgroundColor = Color.White,
        topBar = {
            TopNavGraph(navController = navController)
        },
        bottomBar = {
            BottomBar(navController = navController)
        }
    ){
        BottomNavGraph(navController = navController)
    }
}

@Composable
@Preview
fun Preview(){
    HomeScreen()
}