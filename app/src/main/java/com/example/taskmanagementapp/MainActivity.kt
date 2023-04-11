package com.example.taskmanagementapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.taskmanagementapp.ViewModel.LogInViewModel
import com.example.taskmanagementapp.navigation.RootNavigationGraph
import com.example.taskmanagementapp.ui.theme.TaskManagementAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    private val logInViewModel = LogInViewModel(this)
    private val signInLauncher = registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
        logInViewModel.handleSignInResult(result.data)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            navController = rememberNavController()
            TaskManagementAppTheme {
                RootNavigationGraph(navController = navController, logInViewModel = logInViewModel)
            }
        }
    }

    fun getSignInLauncher() : ActivityResultLauncher<IntentSenderRequest> = signInLauncher
}