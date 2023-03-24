package com.example.taskmanagementapp.ui.theme.home

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreen(){
    Scaffold(
        topBar = {
             HomeAppBar()
        },
        content = {
                  
        },
        bottomBar = {}
    )
}

@Composable
@Preview
fun HomeScreenPreview(){
    HomeScreen()
}