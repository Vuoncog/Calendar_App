package com.example.taskmanagementapp.ui.screens.management

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskmanagementapp.constant.SystemColorSet
import com.example.taskmanagementapp.constant.TaskType
import com.example.taskmanagementapp.constant.ToDoTask
import com.example.taskmanagementapp.data.SharedViewModel
import com.example.taskmanagementapp.ui.Fab
import com.example.taskmanagementapp.ui.screens.calendar.WeeklyCalendar
import java.time.LocalDate
import java.time.ZoneId
import java.util.*

@SuppressLint("MutableCollectionMutableState")
@Composable
fun ManagementContent(
    systemColorSet: SystemColorSet,
    date: Date,
    calendar: Calendar,
    selectedDate: Date,
    onSelectDay: (Date) -> Unit,
    navigateToAddTask: () -> Unit,
    sharedViewModel: SharedViewModel
) {

    val listTask = listOf(
        ToDoTask(TaskType.Running, "Walking", true),
        ToDoTask(TaskType.Shopping, "Go shopping", true),
        ToDoTask(TaskType.PetFood, "Pet Food", false)
    )

    var _listTask by remember { mutableStateOf(listTask) }
    val changeTaskState: (ToDoTask, ToDoTask) -> Unit = { removeTask, addTask ->
        _listTask = _listTask - removeTask
        _listTask = _listTask + addTask
        Log.d("list task: ", _listTask.toString())
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            WeeklyCalendar(
                systemColor = systemColorSet.primaryColor,
                onSelectedDayChange = {},
                currentDate = date,
                calendar = calendar,
                selectedDate = selectedDate,
                onSelectDay = onSelectDay,
                sharedViewModel = sharedViewModel,
                isCalendarContent = false
            )

            TaskInfoCard(
                listTask = _listTask,
                systemColor = systemColorSet.primaryColor,
                subSystemColor = systemColorSet.secondaryColor,
                sticker = systemColorSet.listStickerSet[14]
            )

            TaskState(
                listTask = _listTask,
                isCompleted = false,
                changeTaskState = changeTaskState,
                systemColor = systemColorSet.primaryColor,
                subSystemColor = systemColorSet.secondaryColor
            )
            TaskState(
                listTask = _listTask,
                isCompleted = true,
                changeTaskState = changeTaskState,
                systemColor = systemColorSet.primaryColor,
                subSystemColor = systemColorSet.secondaryColor
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .offset(
                    y = (-56).dp
                ),
            contentAlignment = Alignment.BottomEnd
        ) {
            Fab(
                onFabClicked = navigateToAddTask,
                systemColor = systemColorSet.primaryColor
            )
        }
    }
}

@Preview
@Composable
fun ManagementPreView() {
    val date = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant())
    val calendar = Calendar.getInstance()
    val sharedViewModel: SharedViewModel? = null
    ManagementContent(
        date = date,
        calendar = calendar,
        selectedDate = date,
        onSelectDay = {},
        navigateToAddTask = {},
        sharedViewModel = sharedViewModel!!,
        systemColorSet = SystemColorSet.ORANGE
    )
}
