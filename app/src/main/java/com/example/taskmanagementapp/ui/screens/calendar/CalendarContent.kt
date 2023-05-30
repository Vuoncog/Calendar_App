package com.example.taskmanagementapp.ui.screens.calendar

import TimeGrid
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskmanagementapp.data.SharedViewModel
import com.example.taskmanagementapp.ui.Fab
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
    onSelectDay: (Date) -> Unit,
    navigateToAddTask: () -> Unit,
    sharedViewModel: SharedViewModel? = null
) {
    LaunchedEffect(
        key1 = true,
        block = { sharedViewModel?.getEventInfo(LocalDate.now().toEpochDay(), isCalendarContent = true) })
    val listOffset = mutableListOf<Float>()
    val listSpace = mutableListOf<Float>()
    listOffset.add(0f)
    listSpace.add(0f)
    for (index in sharedViewModel?.listEventsResult!!.indices ) {
        var startTime = sharedViewModel.getHourAndMinute(sharedViewModel.listEventsResult[index].startTime)
        val endTime = sharedViewModel.getHourAndMinute(sharedViewModel.listEventsResult[index].endTime)
        if(startTime > endTime) startTime -= 24
        val offset = endTime - startTime
        val space = startTime + offset
        listOffset.add(
            heightEvent(timeRange = offset)
        )
        listSpace.add(space)

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
            onSelectDay = onSelectDay,
            sharedViewModel = sharedViewModel,
            isCalendarContent = true
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
                timeGridHeightDp = with(localDensity) { coordinates.size.height.toDp() }
            }
            .verticalScroll(
                state = scrollState,
            )
    )

    // Setup Event
    CalendarEvent(
        listEvent = sharedViewModel.listEventsResult,
        listOffset = listOffset,
        listSpace = listSpace,
        state = scrollState,
        height = timeGridHeightDp,
        offset = columnHeightDp,
        sharedViewModel = sharedViewModel
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .offset(
                y = (-56).dp
            )
            .padding(16.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        Fab(onFabClicked = navigateToAddTask)
    }
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
        onSelectDay = {},
        navigateToAddTask = {}
    )
}