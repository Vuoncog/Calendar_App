@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.taskmanagementapp.ui.screens.management

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskmanagementapp.constant.TaskType
import com.example.taskmanagementapp.ui.component.DisplayDate
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

@Preview
@Composable
fun ManagementPreView() {
    ManagementContent(LocalDate.now(), {})
}