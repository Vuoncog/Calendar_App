package com.example.taskmanagementapp.ui.screens.management.task

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskmanagementapp.R
import com.example.taskmanagementapp.constant.RecommendColor
import com.example.taskmanagementapp.ui.screens.profile.bottomsheet.ColorCircle
import com.example.taskmanagementapp.ui.theme.Neutral1
import com.example.taskmanagementapp.ui.theme.SystemColor
import com.example.taskmanagementapp.ui.theme.VisbyTypography

@Composable
fun Theme(
    systemColor: Color = SystemColor
) {
    val recommendColor = listOf(
        RecommendColor.Orange,
        RecommendColor.Green,
        RecommendColor.Purple,
    )

    Column(
        modifier = Modifier.padding(
            top = 8.dp,
            start = 16.dp,
            end = 16.dp
        ),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_paint_board),
                contentDescription = "Paint",
                tint = systemColor
            )

            Text(
                text = "Theme",
                style = VisbyTypography.subtitle1,
                color = Neutral1
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .padding(
                    vertical = 12.dp,
                    horizontal = 16.dp
                )
                .fillMaxWidth()
        ) {
            recommendColor.forEach { displayColor ->
                ColorCircle(
                    onColorCircleClicked = {},
                    color = displayColor,
                    systemColor = systemColor,
                    onColorChange = {},
                    circleSize = 16.dp
                )
            }
        }
    }
}

@Preview
@Composable
fun ThemePreview() {
    Theme()
}