package com.example.taskmanagementapp.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskmanagementapp.data.SharedViewModel
import com.example.taskmanagementapp.ui.theme.SystemColor
import com.example.taskmanagementapp.ui.theme.VisbyTypography

@Composable
fun CustomButton(
    navigateToHome: () -> Unit,
    buttonText: String = "button",
    onClickEvent: (() -> Unit)? = null,
    sharedViewModel: SharedViewModel
) {
    Button(
        modifier = Modifier
            .padding(0.dp)
            .fillMaxWidth()
            .height(48.dp),
        onClick = {
            onClickEvent?.let {
                onClickEvent()
                sharedViewModel.let {
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

}