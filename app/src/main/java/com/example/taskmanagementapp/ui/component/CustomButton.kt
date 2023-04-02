package com.example.taskmanagementapp.ui.component

import android.icu.text.RelativeDateTimeFormatter.RelativeUnit
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskmanagementapp.ViewModel.LogInViewModel
import com.example.taskmanagementapp.ui.theme.SystemColor
import com.example.taskmanagementapp.ui.theme.VisbyTypography
import java.util.logging.Handler
import kotlinx.coroutines.delay as delay1

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CustomButton(
    navigateToHome: () -> Unit,
    buttonText: String = "button",
    onClickEvent: (() -> Unit)? = null,
    logInViewModel: LogInViewModel? = null
) {
    Button(
        modifier = Modifier
            .padding(0.dp)
            .fillMaxWidth()
            .height(48.dp),
        onClick = {
            onClickEvent?.let {
                onClickEvent()
                logInViewModel?.let {
                    val handler : android.os.Handler = android.os.Handler()
                    handler.postDelayed({ it.getCurrentUser()?.let {
                        navigateToHome()
                    } },2900)
                }
            }
        },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = SystemColor,
            contentColor = Color.White,
            disabledBackgroundColor = SystemColor.copy(alpha = 0.4f),
            disabledContentColor = Color.White,
        ),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
            disabledElevation = 0.dp,
            hoveredElevation = 0.dp,
            focusedElevation = 0.dp
        ),
        shape = RoundedCornerShape(40.dp),
    ) {
        Text(
            text = buttonText.uppercase(),
            style = VisbyTypography.button
        )
    }
}

@Preview
@Composable
fun CustomButtonPreview() {
    CustomButton({})
}