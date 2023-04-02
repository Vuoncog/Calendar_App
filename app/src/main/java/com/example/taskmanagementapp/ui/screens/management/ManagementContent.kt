@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.taskmanagementapp.ui.screens.management

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskmanagementapp.R
import com.example.taskmanagementapp.constant.TaskType
import com.example.taskmanagementapp.ui.theme.Neutral2
import com.example.taskmanagementapp.ui.theme.SystemColor
import com.example.taskmanagementapp.ui.theme.VisbyTypography
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import java.time.LocalDate

@Composable
fun ManagementContent(
    currentDay: LocalDate,
    calendarDaySelected: (LocalDate) -> Unit
) {

    val listTask = listOf(
        TaskType.Running,
        TaskType.Shopping,
        TaskType.PetFood,
    )

    val listCompletedTask = listOf(
        TaskType.WalkTheDog
    )

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            DisplayDate(date = currentDay,
            calendarDaySelected = calendarDaySelected)

            TaskInfoCard()

            TaskState(listTask = listTask, isCompleted = false)
            TaskState(listTask = listCompletedTask, isCompleted = true)
        }

    }
}

@Composable
fun DisplayDate(
    date: LocalDate,
    calendarDaySelected: (LocalDate) -> Unit
) {
    val calendarState = rememberSheetState()

    CalendarDialog(
        state = calendarState,
        selection = CalendarSelection.Date {
            calendarDaySelected(it)
        }
    )

    val weekly = date.dayOfWeek.toString().lowercase()
        .replaceFirstChar { it.uppercase() }

    val monthly = date.month.toString().lowercase()
        .replaceFirstChar { it.uppercase() }

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(
                text = weekly,
                style = VisbyTypography.h6,
                color = SystemColor
            )

            Text(
                text = "${date.dayOfMonth} $monthly ${date.year}",
                style = VisbyTypography.h6,
                color = Neutral2
            )
        }

        IconButton(onClick = { calendarState.show() }) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_calendar_settings),
                contentDescription = "Calendar Settings Icon",
                tint = SystemColor
            )
        }
    }
}

@Preview
@Composable
fun ManagementPreView() {
    ManagementContent(LocalDate.now(), {})
}