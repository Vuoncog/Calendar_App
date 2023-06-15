package com.example.taskmanagementapp.ui.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.taskmanagementapp.R
import com.example.taskmanagementapp.constant.TaskType
import com.example.taskmanagementapp.ui.theme.*

@Composable
fun HomeTodoTaskItem(
    taskType: TaskType,
    systemColor: Color = SystemColor,
    isDone: Boolean = false
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .then(
                if (isDone) isDoneModifier(color = BackgroundColorTask)
                else inProgressModifier(
                    color = systemColor,
                    radius = 12.dp
                )
            )
            .padding(8.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .clip(
                        RoundedCornerShape(8.dp)
                    )
                    .then(
                        if (isDone) isDoneModifier(color = Color.White)
                        else inProgressModifier(
                            color = systemColor,
                            radius = 8.dp
                        )
                    )
                    .padding(8.dp),
                imageVector = ImageVector.vectorResource(id = taskType.icon),
                contentDescription = taskType.description,
                tint = if (isDone) Neutral6 else systemColor
            )
            Text(
                text = taskType.description,
                style = VisbyTypography.subtitle2,
                color = if (isDone) Neutral6 else Neutral3
            )
        }

        Text(
            text = "8:00 AM".uppercase(),
            style = VisbyTypography.overline,
            color = Neutral6
        )
    }
}

@SuppressLint("ModifierFactoryExtensionFunction")
fun isDoneModifier(color: Color) = Modifier.background(
    color = color,
)

@SuppressLint("ModifierFactoryExtensionFunction")
fun inProgressModifier(color: Color, radius: Dp) = Modifier
    .border(
        width = 1.dp,
        color = color,
        shape = RoundedCornerShape(radius)
    )

@Preview
@Composable
fun ToDoTaskPreview() {
    HomeTodoTaskItem(taskType = TaskType(R.drawable.ic_bag,""), systemColor = SystemColor, isDone = true)
}

@Preview
@Composable
fun ToDoTaskDonePreview() {
    HomeTodoTaskItem(taskType = TaskType(R.drawable.ic_bag,""), systemColor = SystemColor, isDone = false)
}