package com.example.taskmanagementapp.ui.screens.calendar

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskmanagementapp.data.SharedViewModel
import com.example.taskmanagementapp.ui.theme.Neutral2
import com.example.taskmanagementapp.ui.theme.Neutral5
import com.example.taskmanagementapp.ui.theme.Neutral8
import com.example.taskmanagementapp.ui.theme.VisbyTypography
import com.mrerror.singleRowCalendar.DateUtils
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.ZoneId
import java.util.*

@Composable
fun WeeklyCalendar(
    modifier: Modifier = Modifier,
    systemColor: Color,
    onSelectedDayChange: (Date) -> Unit,
    currentDate: Date,
    calendar: Calendar,
    selectedDate: Date,
    onSelectDay: (Date) -> Unit,
    sharedViewModel: SharedViewModel,
    isCalendarContent : Boolean
) {
    calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY)
    Column(modifier) {
        WeeklyHeader(
            firstDayDate = currentDate,
            systemColor = systemColor,
            selectedDate = selectedDate,
            onSelectDay = onSelectDay,
            onSelectedDayChange = onSelectedDayChange,
            sharedViewModel = sharedViewModel,
            isCalendarContent = isCalendarContent
        )
    }
}

@OptIn(DelicateCoroutinesApi::class)
@Composable
fun WeeklyHeader(
    modifier: Modifier = Modifier,
    systemColor: Color,
    firstDayDate: Date,
    selectedDate: Date,
    onSelectDay: (Date) -> Unit,
    onSelectedDayChange: (Date) -> Unit,
    sharedViewModel: SharedViewModel,
    isCalendarContent: Boolean
) {
    val weekFinalDays =
        DateUtils.getFutureDates(6, Calendar.getInstance().apply { time = firstDayDate })
    val interactionSource = remember { MutableInteractionSource() }
    val coroutineScope = rememberCoroutineScope()

    Row(
        modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        for (day in weekFinalDays) {
            val isSelected = (DateUtils.getDayNumber(selectedDate) == DateUtils.getDayNumber(day)
                    && DateUtils.getMonthNumber(selectedDate) == DateUtils.getMonthNumber(day))
            Column(
                modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = DateUtils.getDay3LettersName(day),
                    style = VisbyTypography.caption,
                    color = Neutral5,
                    textAlign = TextAlign.Center
                )
                Text(text = DateUtils.getDayNumber(day),
                    style = VisbyTypography.subtitle1,
                    fontSize = 16.sp,
                    color = if (isSelected) Neutral8 else Neutral2,

                    modifier = (if (isSelected) Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                        .drawBehind {
                            drawCircle(
                                color = systemColor, radius = this.size.height
                            )
                        }
                    else Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)).clickable(
                        interactionSource = interactionSource,
                        indication = null
                    ) {
                        onSelectDay(day)
                        onSelectedDayChange(day)
                        if(isCalendarContent){
                            sharedViewModel.dateOfEvent = day.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toEpochDay()
                            coroutineScope.launch {sharedViewModel.getEventInfo()}
                        }
                        else{
                            sharedViewModel.dateOfTask = day.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toEpochDay()
                            coroutineScope.launch {sharedViewModel.getToDoTask()}
                        }
                    },
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}