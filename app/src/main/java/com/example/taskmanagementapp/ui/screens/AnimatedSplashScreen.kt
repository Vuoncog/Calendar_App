package com.example.taskmanagementapp.ui.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.taskmanagementapp.R
import kotlinx.coroutines.delay

@Composable
fun AnimatedSplashScreen(
    navController: NavHostController,
    route: String
) {
    var startAnimation by remember { mutableStateOf(false)}
    val alphaAnimation = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
    animationSpec = tween(
        durationMillis = 3000
    ))

    LaunchedEffect(key1 = true){
        startAnimation = true
        delay(4000)
        navController.popBackStack()
        navController.navigate(route)
    }

    Splash(alpha = alphaAnimation.value)
}

@Composable
fun Splash(
    alpha: Float
) {
    Box(modifier = Modifier
        .background(color = Color(0xFFFFCAA5))
        .fillMaxSize()
        .alpha(alpha),
    contentAlignment = Alignment.Center){
        Icon(
            modifier = Modifier.size(192.dp),
            imageVector = ImageVector.vectorResource(id = R.drawable.scheme_logo),
            contentDescription = "Scheme Logo",
        )
    }
}

@Preview
@Composable
fun SplashPreview() {
    Splash(1f)
}