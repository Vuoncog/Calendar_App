@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.taskmanagementapp.ui.screens.task

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.clock.ClockDialog
import com.maxkeppeler.sheets.clock.models.ClockConfig
import com.maxkeppeler.sheets.clock.models.ClockSelection
import com.mrerror.singleRowCalendar.DateUtils
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

@Composable
fun Time(
    systemColor: Color = SystemColor
): Pair<Long, Long> {
    var startTime by remember { mutableStateOf(0L) }
    var endTime by remember { mutableStateOf(0L) }
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
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(vertical = 12.dp)
        ) {
            startTime = TimeSetup(
                isStart = true,
                systemColor = systemColor
            )
            endTime = TimeSetup(
                isStart = false,
                systemColor = systemColor
            )
        }
    }
    return Pair(startTime, endTime)
}

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeSetup(
    systemColor: Color = SystemColor,
    isStart: Boolean
): Long {
    val currentDay = if (isStart) Date() else Date(Date().time + (1 * 60 * 60 * 1000))
    var dateFormatter by remember { mutableStateOf(dateFormatter(currentDay)) }
    var timeFormatter by remember { mutableStateOf(timeFormatter(currentDay)) }
    val timestamp by remember { mutableStateOf(LocalDateTime.now()) }
    var mHour by remember { mutableStateOf(LocalDateTime.now().hour) }
    var mMinute by remember { mutableStateOf(LocalDateTime.now().minute) }
    var mDay by remember { mutableStateOf(LocalDate.now().dayOfMonth) }
    var mMonth by remember { mutableStateOf(LocalDate.now().monthValue) }
    var mYear by remember { mutableStateOf(LocalDate.now().year) }

    val timeState = rememberSheetState()
    val calendarState = rememberSheetState()
    CalendarDialog(
        state = calendarState,
        config = CalendarConfig(),
        selection = CalendarSelection.Date { date ->
            val _date = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant())
            dateFormatter = dateFormatter(_date)
            mDay = date.dayOfMonth
            mMonth = date.monthValue
            mYear = date.year
        },
    )
    ClockDialog(
        state = timeState,
        selection = ClockSelection.HoursMinutes { hours, minutes ->
            mHour = hours
            mMinute = minutes
            timeFormatter = "${mHour}:${mMinute}"
        },
        config = ClockConfig(is24HourFormat = true),
    )

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

        Text(text = dateFormatter,
            style = VisbyTypography.body2,
            color = Neutral2,
            modifier = Modifier
                .weight(1f)
                .clickable {
                    calendarState.show()
                })

        Text(
            text = timeFormatter,
            style = VisbyTypography.body2,
            color = Neutral2,
            modifier = Modifier.clickable {
                timeState.show()
            }
        )
    }
    return timestamp.withDayOfMonth(mDay).withMonth(mMonth).withYear(mYear).withHour(mHour)
        .withMinute(mMinute).atZone(ZoneId.systemDefault()).toInstant().epochSecond
}

fun weekday(date: Date): String = DateUtils.getDay3LettersName(date)
fun dayName(date: Date): String = DateUtils.getDayNumber(date)
fun monthName(date: Date): String = DateUtils.getMonthName(date)
fun year(date: Date): String = DateUtils.getYear(date)
fun hour(date: Date): String = date.hours.toString()
fun minute(date: Date): String = date.minutes.toString()
fun dateFormatter(date: Date): String =
    "${weekday(date)}, ${dayName(date)} ${monthName(date)}, ${year(date)}"

fun timeFormatter(date: Date): String = "${hour(date)}:${minute(date)}"

@Preview
@Composable
fun TimePreview() {
    Time()
}