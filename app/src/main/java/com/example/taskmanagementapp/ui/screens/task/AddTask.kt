package com.example.taskmanagementapp.ui.screens.task

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.taskmanagementapp.constant.EventInfo
import com.example.taskmanagementapp.constant.SystemColorSet
import com.example.taskmanagementapp.data.SharedViewModel
import com.example.taskmanagementapp.ui.theme.NeutralBorder
import com.google.gson.Gson

@Composable
fun AddTask(
    sharedViewModel: SharedViewModel,
    haveSubtask: Boolean = false,
    systemColorSet: SystemColorSet,
    eventInfo: String? = null
) {
    if(eventInfo != null){
        sharedViewModel.oldEventInfo = Gson().fromJson(eventInfo, EventInfo::class.java)
    }
    Column(
        modifier = Modifier.background(color = Color.White)
    ) {
        sharedViewModel.titleAndDetailEvent.value = Title(
            systemColor = systemColorSet.primaryColor,
            titleAndDetail = if (eventInfo != null) Pair(
                sharedViewModel.oldEventInfo.title,
                sharedViewModel.oldEventInfo.detail!!
            ) else null
        )
        Divider(
            thickness = 1.dp,
            color = NeutralBorder
        )
        sharedViewModel.startAndEndEvent.value = Time(
            systemColor = systemColorSet.primaryColor,
            mEventInfo = if (eventInfo != null) sharedViewModel.oldEventInfo else null
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
        Theme()
        Divider(
            thickness = 1.dp,
            color = NeutralBorder
        )
        if (haveSubtask) {
            Subtask(
                systemColor = systemColorSet.primaryColor
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
