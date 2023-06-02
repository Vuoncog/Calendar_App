package com.example.taskmanagementapp.ui.screens.task

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.taskmanagementapp.data.SharedViewModel
import com.example.taskmanagementapp.ui.theme.NeutralBorder

@Composable
fun AddTask(
    sharedViewModel: SharedViewModel,
    haveSubtask: Boolean = false
) {
    Column(
        modifier = Modifier.background(color = Color.White)
    ) {
        sharedViewModel.titleAndDetailEvent.value = Title()
        Divider(
            thickness = 1.dp,
            color = NeutralBorder
        )
        sharedViewModel.startAndEndEvent.value = Time()
        Divider(
            thickness = 1.dp,
            color = NeutralBorder
        )
        Reminder()
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
            Subtask()
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
