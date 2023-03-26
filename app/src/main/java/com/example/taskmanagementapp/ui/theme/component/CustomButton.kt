package com.example.taskmanagementapp.ui.theme.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskmanagementapp.ui.theme.Primary4
import com.example.taskmanagementapp.ui.theme.Primary5
import com.example.taskmanagementapp.ui.theme.VisbyTypography

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CustomButton(
    buttonText: String = "button"
) {
    Button(
        modifier = Modifier
            .padding(0.dp)
            .fillMaxWidth()
            .height(48.dp),
        onClick = {

        },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Primary4,
            contentColor = Color.White,
            disabledBackgroundColor = Primary5.copy(alpha = 0.4f),
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
    CustomButton()
}