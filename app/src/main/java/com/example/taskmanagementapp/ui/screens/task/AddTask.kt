package com.example.taskmanagementapp.ui.screens.task

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.taskmanagementapp.constant.SystemColorSet
import com.example.taskmanagementapp.data.SharedViewModel
import com.example.taskmanagementapp.ui.theme.NeutralBorder

@Composable
fun AddTask(
    sharedViewModel: SharedViewModel,
    haveSubtask: Boolean = false,
    systemColorSet: SystemColorSet
) {
    Column(
        modifier = Modifier.background(color = Color.White)
    ) {
        sharedViewModel.titleAndDetailEvent.value = Title(systemColor = systemColorSet.primaryColor)
        Divider(
            thickness = 1.dp,
            color = NeutralBorder
        )
        sharedViewModel.startAndEndEvent.value = Time(systemColor = systemColorSet.primaryColor)
        Divider(
            thickness = 1.dp,
            color = NeutralBorder
        )
        Reminder(systemColor = systemColorSet.primaryColor)
        Divider(
            thickness = 1.dp,
            color = NeutralBorder
        )
        Theme(systemColorSet = systemColorSet)
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
