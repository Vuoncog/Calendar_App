package com.example.taskmanagementapp.ui.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.taskmanagementapp.R

@Composable
fun RecoverPasswordContent() {
    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(id = R.drawable.login_background),
        contentDescription = "Background",
        contentScale = ContentScale.FillHeight
    )
}