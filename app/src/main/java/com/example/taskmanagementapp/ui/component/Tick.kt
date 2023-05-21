package com.example.taskmanagementapp.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.taskmanagementapp.R
import com.example.taskmanagementapp.constant.ToDoTask
import com.example.taskmanagementapp.ui.theme.Neutral8
import com.example.taskmanagementapp.ui.theme.Primary4
import com.example.taskmanagementapp.ui.theme.SystemColor

@Composable
fun Tick(
    toDoTask: ToDoTask,
    onChangeStateClicked: (ToDoTask, ToDoTask) -> Unit,
) {
    IconButton(
        onClick = {
            val addToDoTask = ToDoTask(
                taskType = toDoTask.taskType,
                taskName = toDoTask.taskName,
                isDone = !toDoTask.isDone
            )
            onChangeStateClicked(toDoTask, addToDoTask)
        },
        modifier = Modifier
            .size(24.dp)
            .clip(CircleShape)
            .border(
                width = 1.dp,
                color = SystemColor,
                shape = CircleShape
            )
            .background(color = if (toDoTask.isDone) Primary4 else Color.Transparent)
            .padding(0.dp)
    ) {
        Icon(
            modifier = Modifier.size(16.dp),
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_tick),
            contentDescription = "content description",
            tint = if (toDoTask.isDone) Neutral8 else Color.Transparent,
        )
    }
}