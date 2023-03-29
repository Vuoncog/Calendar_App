package com.example.taskmanagementapp.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskmanagementapp.R
import com.example.taskmanagementapp.ui.theme.SystemColor
import com.example.taskmanagementapp.ui.theme.VisbyTypography

@Composable
fun Due(
    flagColor: Color
) {
    Row() {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_flag_solid),
            contentDescription = "Deadline",
            tint = flagColor
        )
        Text(
            text = "9:00 AM",
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(start = 8.dp),
            style = VisbyTypography.subtitle2
        )
    }
}

@Preview
@Composable
fun DuePreview() {
    Due(SystemColor)
}