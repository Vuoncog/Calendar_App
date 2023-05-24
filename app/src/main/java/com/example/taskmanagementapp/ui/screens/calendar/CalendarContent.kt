package com.example.taskmanagementapp.ui.screens.calendar

import TimeGrid
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskmanagementapp.constant.EventInfo
import com.example.taskmanagementapp.ui.theme.*
import java.time.LocalDate
import java.time.ZoneId
import java.util.*


@SuppressLint("UnrememberedMutableState")
@Composable
fun CalendarContent(
    date: Date,
    calendar: Calendar,
    selectedDate: Date,
    onSelectDay: (Date) -> Unit
) {
    val eventList = listOf(
        EventInfo(
            color = BackgroundColorTask,
            title = "Sleep",
            startTime = 3.7f,
            timeRange = 2.6f
        ),
        EventInfo(
            color = Primary1,
            title = "Running",
            startTime = 5f,
            timeRange = 2.8f
        ),
        EventInfo(
            color = Primary3,
            title = "Running",
            startTime = 6f,
            timeRange = 3f
        ),
    )
    val listOffset = mutableListOf<Float>()
    val listSpace = mutableListOf<Float>()
    listOffset.add(0f)
    listSpace.add(0f)
    for (event in eventList) {
        listOffset.add(
            heightEvent(timeRange = event.timeRange)
        )
        listSpace.add(
            event.timeRange + event.startTime
        )
    }
    val scrollState by mutableStateOf(rememberScrollState())
    val localDensity = LocalDensity.current
    var columnHeightDp by remember {
        mutableStateOf(0.dp)
    }
    var timeGridHeightDp by remember {
        mutableStateOf(0.dp)
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .padding(
                horizontal = 16.dp,
                vertical = 20.dp
            )
            .onGloballyPositioned { coordinates ->
                columnHeightDp = with(localDensity) { coordinates.size.height.toDp() }
            }
    ) {
        WeeklyCalendar(
            onSelectedDayChange = {},
            currentDate = date,
            calendar = calendar,
            selectedDate = selectedDate,
            onSelectDay = onSelectDay
        )

        Divider(
            thickness = 1.dp,
            color = Neutral7
        )
    }

    // Split timeline
    TimeGrid(
        listTime = (0..25).toList(),
        modifier = Modifier
            .offset(y = columnHeightDp)
            .padding(
                horizontal = 16.dp,
                vertical = 20.dp
            )
            .onGloballyPositioned { coordinates ->
                // Set column height using the LayoutCoordinates
                timeGridHeightDp = with(localDensity) { coordinates.size.height.toDp() }
            }
            .verticalScroll(
                state = scrollState,
            )
    )

    // Setup Event
    CalendarEvent(
        listEvent = eventList,
        listOffset = listOffset,
        listSpace = listSpace,
        state = scrollState,
        height = timeGridHeightDp,
        offset = columnHeightDp,
    )
}


@Preview
@Composable
fun CalendarPreview() {
    val date = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant())
    val calendar = Calendar.getInstance()
    CalendarContent(
        date = date,
        calendar = calendar,
        selectedDate = date,
        onSelectDay = {}
    )
}