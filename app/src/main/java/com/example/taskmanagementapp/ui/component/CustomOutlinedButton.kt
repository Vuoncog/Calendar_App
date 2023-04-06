package com.example.taskmanagementapp.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskmanagementapp.R
import com.example.taskmanagementapp.ui.theme.Neutral2
import com.example.taskmanagementapp.ui.theme.SystemColor
import com.example.taskmanagementapp.ui.theme.VisbyTypography

@Composable
fun CustomOutlinedButton(
    navigateToHome: () -> Unit,
    whichPlatform: String,
    onClickEvent : (() -> Unit)? = null
) {
    Button(
        modifier = Modifier
            .height(48.dp)
            .fillMaxWidth(),
        onClick = {
                navigateToHome()
        },
        shape = RoundedCornerShape(40.dp),
        border = BorderStroke(1.dp, SystemColor),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = Neutral2,
            backgroundColor = Color.Transparent,
            disabledContentColor = SystemColor.copy(alpha = 0.4f)
        ),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
            disabledElevation = 0.dp,
            hoveredElevation = 0.dp,
            focusedElevation = 0.dp
        )
    ) {
        when (whichPlatform) {
            "Google" -> Image(
                modifier = Modifier.size(24.dp),
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_google),
                contentDescription = "Google Icon"
            )
            "Facebook" -> Image(
                modifier = Modifier.size(24.dp),
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_facebook),
                contentDescription = "Facebook Icon"
            )
        }

        Spacer(modifier = Modifier.padding(start = 8.dp))
        Text(
            text = "Continue with $whichPlatform",
            style = VisbyTypography.subtitle2,
        )

    }
}

@Preview
@Composable
fun CustomOutlinedButtonPreview() {
    CustomOutlinedButton({},"Google")
}