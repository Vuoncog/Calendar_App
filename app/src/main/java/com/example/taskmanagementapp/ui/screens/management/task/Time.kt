package com.example.taskmanagementapp.ui.screens.management.task

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
import com.example.taskmanagementapp.ui.theme.Neutral1
import com.example.taskmanagementapp.ui.theme.Neutral2
import com.example.taskmanagementapp.ui.theme.SystemColor
import com.example.taskmanagementapp.ui.theme.VisbyTypography
import com.mrerror.singleRowCalendar.DateUtils
import java.util.*

@Composable
fun Time(
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
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_clock_circle),
                contentDescription = "Alarm",
                tint = systemColor
            )

            Text(
                text = "Time",
                style = VisbyTypography.subtitle1,
                color = Neutral1
            )
        }
        Column(verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.padding(vertical = 12.dp)) {
            TimeSetup(isStart = true)
            TimeSetup(isStart = false)
        }
    }
}

@Composable
fun TimeSetup(
    systemColor: Color = SystemColor,
    isStart: Boolean
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
                id =
                if (isStart) R.drawable.ic_login
                else R.drawable.ic_logout_outline
            ),
            contentDescription = "Start",
            tint = systemColor,
            modifier = Modifier.size(20.dp)
        )

        val currentDay = Calendar.getInstance().time
        val weekday = DateUtils.getDay3LettersName(currentDay)
        val dayName = DateUtils.getDayNumber(currentDay)
        val monthName = DateUtils.getMonthName(currentDay)
        val year = DateUtils.getYear(currentDay)
        val dateFormatter = "$weekday, $dayName $monthName, $year"
        Text(text = dateFormatter,
            style = VisbyTypography.body2,
            color = Neutral2,
            modifier = Modifier
                .weight(1f)
                .clickable {

                })

        Text(
            text = "10:00",
            style = VisbyTypography.body2,
            color = Neutral2,
            modifier = Modifier.clickable {

            }
        )
    }
}

@Preview
@Composable
fun TimePreview() {
    Time()
}