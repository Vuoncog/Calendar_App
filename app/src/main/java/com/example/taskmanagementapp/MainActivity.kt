package com.example.taskmanagementapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.taskmanagementapp.data.SharedViewModel
import com.example.taskmanagementapp.navigation.RootNavigationGraph
import com.example.taskmanagementapp.ui.theme.M3Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    private val sharedViewModel : SharedViewModel by viewModels()
    private val signInLauncher =
        registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
            sharedViewModel.handleSignInResult(result.data)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            navController = rememberNavController()
            sharedViewModel.mainActivity = this
            M3Theme {
                RootNavigationGraph(
                    navController = navController,
                    sharedViewModel = sharedViewModel
                )
            }
        }
    }

    fun getSignInLauncher(): ActivityResultLauncher<IntentSenderRequest> = signInLauncher

     fun getMainActivity() : MainActivity = this
}