package com.example.taskmanagementapp.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.taskmanagementapp.R
import com.example.taskmanagementapp.ui.theme.Neutral2
import com.example.taskmanagementapp.ui.theme.SystemColor
import com.example.taskmanagementapp.ui.theme.VisbyTypography
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
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
        verticalAlignment = Alignment.CenterVertically,
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