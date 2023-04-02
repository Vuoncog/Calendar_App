package com.example.taskmanagementapp.ui.screens.management

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.taskmanagementapp.constant.TaskType
import com.example.taskmanagementapp.ui.component.IconType
import com.example.taskmanagementapp.ui.component.Tick
import com.example.taskmanagementapp.ui.theme.BackgroundColorTask
import com.example.taskmanagementapp.ui.theme.Neutral2
import com.example.taskmanagementapp.ui.theme.SystemColor
import com.example.taskmanagementapp.ui.theme.VisbyTypography

@Composable
fun TaskState(
    listTask: List<TaskType>,
    isCompleted: Boolean
) {
    val statusTitle = if (isCompleted) "Completed" else "Not started"

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = statusTitle,
            style = VisbyTypography.h6,
            color = Neutral2
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items = listTask) { item ->
                TaskDisplay(
                    taskType = item,
                    isCompleted = isCompleted
                )
            }
        }
    }
}

@Composable
fun TaskDisplay(
    taskType: TaskType,
    isCompleted: Boolean
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(color = BackgroundColorTask)
            .padding(8.dp),
        verticalAlignment = CenterVertically
    ) {
        IconType(icon = taskType.icon)

        Text(
            text = taskType.description,
            style = VisbyTypography.subtitle1,
            color = SystemColor,
            modifier = Modifier.weight(1f)
        )

        Tick(isCompleted)
    }

}