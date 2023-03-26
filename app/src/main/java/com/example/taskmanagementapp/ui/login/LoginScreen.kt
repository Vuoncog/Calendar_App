package com.example.taskmanagementapp.ui.login

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.taskmanagementapp.ui.home.HomeContent

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LoginScreen() {
    Scaffold(
        content = {
            LoginContent()
        },

        )
}