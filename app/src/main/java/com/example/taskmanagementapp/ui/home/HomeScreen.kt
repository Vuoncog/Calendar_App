package com.example.taskmanagementapp.ui.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreen() {
    Scaffold(
        backgroundColor = Color.White,
        topBar = {
            HomeAppBar()
        },
        content = {
            HomeContent()
        },
        bottomBar = {}
    )
}

@Composable
@Preview
fun HomeScreenPreview() {
    HomeScreen()
}