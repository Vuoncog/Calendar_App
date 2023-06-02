package com.example.taskmanagementapp.ui.screens.task

import androidx.compose.foundation.clickable
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
import com.example.taskmanagementapp.ui.theme.*

@Composable
fun Reminder(
    systemColor: Color = SystemColor
) {
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
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_alarm),
                contentDescription = "Alarm",
                tint = systemColor
            )

            Text(
                text = "Set Reminder",
                style = VisbyTypography.subtitle1,
                color = Neutral1
            )
        }
        Box(modifier = Modifier.padding(vertical = 12.dp)) {
            ReminderSetup()
        }
    }
}

@Composable
fun ReminderSetup(
    systemColor: Color = SystemColor,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.padding(
            start = 32.dp,
            end = 16.dp
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(
                id = R.drawable.ic_clock_circle_outline
            ),
            contentDescription = "Start",
            tint = systemColor,
            modifier = Modifier.size(20.dp)
        )

        Text(
            text = "15 minutes before",
            style = VisbyTypography.body2,
            color = Neutral2,
            modifier = Modifier
                .weight(1f)
        )

        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_chevron_right),
            contentDescription = "Expand",
            tint = Neutral5,
            modifier = Modifier
                .size(20.dp)
                .clickable {

                }
        )
    }
}

@Preview
@Composable
fun ReminderPreview() {
    Reminder()
}