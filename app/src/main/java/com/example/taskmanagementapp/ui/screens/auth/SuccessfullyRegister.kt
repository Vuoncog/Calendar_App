package com.example.taskmanagementapp.ui.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.taskmanagementapp.R
import com.example.taskmanagementapp.constant.SystemColorSet
import com.example.taskmanagementapp.data.SharedViewModel
import com.example.taskmanagementapp.ui.component.CustomButton
import com.example.taskmanagementapp.ui.theme.Neutral6
import com.example.taskmanagementapp.ui.theme.VisbyTypography

@Composable
fun SuccessfullyRegister(
    systemColor: Color = SystemColorSet.ORANGE.primaryColor,
    sharedViewModel: SharedViewModel,
    onClickedEvent: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .align(Center)
        ) {
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.seal_congrat),
                contentDescription = "Congratulation",

                )

            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Register completely",
                    style = VisbyTypography.h5,
                    color = systemColor
                )

                Text(
                    text = "Let’s back to log-in screen for\n" +
                            "customizing your app",
                    style = VisbyTypography.subtitle2,
                    color = Neutral6,
                )
            }

            CustomButton(
                navigateToHome = { },
                buttonText = "Back to login screen",
                sharedViewModel = sharedViewModel,
                onClickEvent = onClickedEvent
            )

        }

    }
}