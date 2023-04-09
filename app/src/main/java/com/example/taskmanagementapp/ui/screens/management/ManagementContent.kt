package com.example.taskmanagementapp.ui.screens.management

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskmanagementapp.constant.TaskType
import com.example.taskmanagementapp.ui.screens.calendar.WeeklyCalendar
import java.time.LocalDate
import java.time.ZoneId
import java.util.*

@Composable
fun ManagementContent(
    date: Date,
    calendar: Calendar,
    selectedDate: Date,
    onSelectDay: (Date) -> Unit
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
//            DisplayDate(date = currentDay,
//            calendarDaySelected = calendarDaySelected)
            WeeklyCalendar(
                onSelectedDayChange = {},
                currentDate = date,
                calendar = calendar,
                selectedDate = selectedDate,
                onSelectDay = onSelectDay
            )

            TaskInfoCard()

            TaskState(listTask = listTask, isCompleted = false)
            TaskState(listTask = listCompletedTask, isCompleted = true)
        }

    }
}

@Preview
@Composable
fun ManagementPreView() {
    val date = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant())
    val calendar = Calendar.getInstance()
    ManagementContent(
        date = date,
        calendar = calendar,
        selectedDate = date,
        onSelectDay = {}
    )
}
