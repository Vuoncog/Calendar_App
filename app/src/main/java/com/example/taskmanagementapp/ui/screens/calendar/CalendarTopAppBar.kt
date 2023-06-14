package com.example.taskmanagementapp.ui.screens.calendar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskmanagementapp.R
import com.example.taskmanagementapp.ui.theme.Neutral1
import com.example.taskmanagementapp.ui.theme.Primary4
import com.example.taskmanagementapp.ui.theme.VisbyTypography
import com.mrerror.singleRowCalendar.DateUtils
import java.time.LocalDate
import java.time.ZoneId
import java.util.*


@Composable
fun CalendarTopAppBar(
    onPrevWeekClicked: (Date) -> Unit,
    onNextWeekClicked: (Date) -> Unit,
    currentDay: Date,
    systemColor: Color
) {
    CalendarAppBar(
        onNextWeekClicked = onNextWeekClicked,
        onPrevWeekClicked = onPrevWeekClicked,
        currentDay = currentDay,
        systemColor = systemColor
    )
}

@Composable
fun CalendarAppBar(
    onPrevWeekClicked: (Date) -> Unit,
    onNextWeekClicked: (Date) -> Unit,
    currentDay: Date,
    systemColor: Color
) {
    val dayName = DateUtils.getDayNumber(currentDay)
    val monthName = DateUtils.getMonthName(currentDay)
    val weekFinalDays =
        DateUtils.getFutureDates(6, Calendar.getInstance().apply { time = currentDay })
    val weekFinalDate = weekFinalDays.last()
    val fDayName = DateUtils.getDayNumber(weekFinalDate)
    val fMonthName = DateUtils.getMonthName(weekFinalDate)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.calendar_top_background),
            contentDescription = "Image Top App Bar",
            contentScale = ContentScale.FillBounds
        )
    }

    TopAppBar(
        title = {
            Text(
                buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Neutral1,
                            fontStyle = VisbyTypography.h6.fontStyle,
                            fontWeight = VisbyTypography.h3.fontWeight,
                            fontSize = VisbyTypography.h6.fontSize
                        )
                    ) {
                        append("$dayName ")
                    }
                    withStyle(
                        style = SpanStyle(
                            color = systemColor,
                            fontStyle = VisbyTypography.h6.fontStyle,
                            fontWeight = VisbyTypography.h5.fontWeight,
                            fontSize = VisbyTypography.h6.fontSize
                        )
                    ) {
                        append(monthName)
                    }
                    withStyle(
                        style = SpanStyle(
                            color = Neutral1,
                            fontStyle = VisbyTypography.h6.fontStyle,
                            fontWeight = VisbyTypography.h3.fontWeight,
                            fontSize = VisbyTypography.h6.fontSize
                        )
                    ) {
                        append(" - $fDayName ")
                    }
                    withStyle(
                        style = SpanStyle(
                            color = systemColor,
                            fontStyle = VisbyTypography.h6.fontStyle,
                            fontWeight = VisbyTypography.h5.fontWeight,
                            fontSize = VisbyTypography.h6.fontSize
                        )
                    ) {
                        append(fMonthName)
                    }
                },
            )
        },
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        actions = {
            NextWeekIcon(
                nextDayClicked = onNextWeekClicked,
                currentDay = currentDay
            )
        },
        navigationIcon = {
            PreviousWeekIcon(
                previousDayClicked = onPrevWeekClicked,
                currentDay = currentDay
            )
        }
    )
}

@Composable
fun PreviousWeekIcon(
    previousDayClicked: (Date) -> Unit,
    currentDay: Date
) {
    IconButton(
        onClick = {
            val calendar = Calendar.getInstance()
            calendar.time = currentDay
            calendar.add(Calendar.DATE, -7)
            previousDayClicked(calendar.time)
        }
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_chevron_left),
            contentDescription = "Previous day",
            tint = Neutral1
        )
    }
}

@Composable
fun NextWeekIcon(
    nextDayClicked: (Date) -> Unit,
    currentDay: Date
) {
    val weekFinalDays =
        DateUtils.getFutureDates(6, Calendar.getInstance().apply { time = currentDay })
    val weekFinalDate = weekFinalDays.last()
    IconButton(
        onClick = {
            val calendar = Calendar.getInstance()
            calendar.time = weekFinalDate
            calendar.add(Calendar.DATE, 1)
            nextDayClicked(calendar.time)
        }
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_chevron_right),
            contentDescription = "Next day",
            tint = Neutral1
        )
    }
}

@Preview
@Composable
fun MemoTopAppBarPreview() {
    val date = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant())

    CalendarTopAppBar({ }, {}, date, Primary4)
}