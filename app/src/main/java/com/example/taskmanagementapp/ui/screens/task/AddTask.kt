package com.example.taskmanagementapp.ui.screens.task

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.taskmanagementapp.constant.EventInfo
import com.example.taskmanagementapp.constant.SystemColorSet
import com.example.taskmanagementapp.constant.ToDoTask
import com.example.taskmanagementapp.data.SharedViewModel
import com.example.taskmanagementapp.ui.theme.NeutralBorder
import com.google.gson.Gson
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

@Composable
fun AddTask(
    sharedViewModel: SharedViewModel,
    isToDoTask: Boolean = false,
    systemColorSet: SystemColorSet,
    eventInfo: String? = null,
    taskInfo: String? = null
) {
    if (isToDoTask) {
        sharedViewModel.dateOfTask =
            Date(sharedViewModel.startAndEnd.value.first * 1000).toInstant().atZone(
                ZoneId.systemDefault()
            ).toLocalDate().toEpochDay()
    }
    else{
        sharedViewModel.dateOfEvent =
            Date(sharedViewModel.startAndEnd.value.first * 1000).toInstant().atZone(
                ZoneId.systemDefault()
            ).toLocalDate().toEpochDay()
    }
    eventInfo?.let { sharedViewModel.oldEventInfo = Gson().fromJson(it, EventInfo::class.java) }
    if (eventInfo == null) {
        sharedViewModel.oldEventInfo = EventInfo()
    }
    if (taskInfo == null) {
        sharedViewModel.oldTaskInfo = ToDoTask()
    } else {
        sharedViewModel.oldTaskInfo = Gson().fromJson(taskInfo, ToDoTask::class.java)
    }
    Column(
        modifier = Modifier.background(color = Color.White)
    ) {
        sharedViewModel.titleAndDetail.value = Title(
            systemColor = systemColorSet.primaryColor,
            subSystemColor = systemColorSet.secondaryColor,
            titleAndDetail = if (eventInfo != null) Pair(
                sharedViewModel.oldEventInfo.title,
                sharedViewModel.oldEventInfo.detail!!
            )
            else if (taskInfo != null) Pair(
                sharedViewModel.oldTaskInfo.taskName,
                sharedViewModel.oldTaskInfo.taskType.description
            )
            else null,
            sharedViewModel = sharedViewModel
        )
        Divider(
            thickness = 1.dp,
            color = NeutralBorder
        )
        sharedViewModel.startAndEnd.value = Time(
            systemColor = systemColorSet.primaryColor,
            mEventInfo = if (eventInfo != null) sharedViewModel.oldEventInfo else null,
            isToDoTask = isToDoTask,
            mToDoTask = if (taskInfo != null) sharedViewModel.oldTaskInfo else null
        )
        Divider(
            thickness = 1.dp,
            color = NeutralBorder
        )
        Reminder(systemColor = systemColorSet.primaryColor)
        Divider(
            thickness = 1.dp,
            color = NeutralBorder
        )
        if (!isToDoTask) {
            Theme(sharedViewModel = sharedViewModel)
            Divider(
                thickness = 1.dp,
                color = NeutralBorder
            )
        }
        if (isToDoTask) {
            Subtask(
                systemColor = systemColorSet.primaryColor,
                sharedViewModel = sharedViewModel
            )
            Divider(
                thickness = 1.dp,
                color = NeutralBorder
            )
        }
    }
}

/*
@Preview
@Composable
fun AddTaskPreview() {
    AddTask()
}*/
