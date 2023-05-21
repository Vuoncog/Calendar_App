package com.example.taskmanagementapp.ui.screens.management.task

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskmanagementapp.ui.theme.NeutralBorder

@Composable
fun AddTask() {
    Column(
        modifier = Modifier.background(color = Color.White)
    ) {
        Title()
        Divider(
            thickness = 1.dp,
            color = NeutralBorder
        )
        Time()
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
    }
}

@Preview
@Composable
fun AddTaskPreview() {
    AddTask()
}