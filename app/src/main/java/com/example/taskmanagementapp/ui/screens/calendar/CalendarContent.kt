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
import com.example.taskmanagementapp.ui.component.DisplayDate
import com.example.taskmanagementapp.ui.theme.*
import java.time.LocalDate

@SuppressLint("UnrememberedMutableState")
@Composable
fun CalendarContent(
    currentDay: LocalDate,
    calendarDaySelected: (LocalDate) -> Unit
) {
    val eventList = listOf(
        EventInfo(
            color = BackgroundColorTask,
            eventName = "Sleep",
            startTime = 3.7f,
            timeRange = 2.6f
        ),
        EventInfo(
            color = Primary1,
            eventName = "Running",
            startTime = 5f,
            timeRange = 2.8f
        ),
        EventInfo(
            color = Primary3,
            eventName = "Running",
            startTime = 6f,
            timeRange = 3f
        ),
        EventInfo(
            color = Primary2,
            eventName = "Asss",
            startTime = 11f,
            timeRange = 2f
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
//    for (index in 1 until eventList.size) {
//        listSpace.add(
//            eventList[index].startTime + eventList[index].timeRange
//        )
//    }
    val scrollState by mutableStateOf(rememberScrollState())
    val localDensity = LocalDensity.current
    var columnHeightDp by remember {
        mutableStateOf(0.dp)
    }
    var timeGridHeightDp by remember {
        mutableStateOf(0.dp)
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp
            )
            .onGloballyPositioned { coordinates ->
                columnHeightDp = with(localDensity) { coordinates.size.height.toDp() }
            }
    ) {
        DisplayDate(
            date = currentDay,
            calendarDaySelected = calendarDaySelected
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
    CalendarContent(
        currentDay = LocalDate.now(),
        calendarDaySelected = {}
    )
}