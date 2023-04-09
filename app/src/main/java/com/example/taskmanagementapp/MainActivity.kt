package com.example.taskmanagementapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.taskmanagementapp.ViewModel.LogInViewModel
import com.example.taskmanagementapp.navigation.RootNavigationGraph
import com.example.taskmanagementapp.ui.theme.TaskManagementAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            navController = rememberNavController()
            TaskManagementAppTheme {
                RootNavigationGraph(navController = navController, logInViewModel = LogInViewModel(this))
            }
        }
    }
}