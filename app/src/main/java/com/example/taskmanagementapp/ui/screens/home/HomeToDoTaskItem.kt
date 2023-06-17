package com.example.taskmanagementapp.ui.screens.home

import android.annotation.SuppressLint
import android.util.Log
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
import com.example.taskmanagementapp.constant.ToDoTask
import com.example.taskmanagementapp.ui.theme.*
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun HomeTodoTaskItem(
    systemColor: Color = SystemColor,
    toDoTask: ToDoTask
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .then(
                if (toDoTask.getDone()) isDoneModifier(color = BackgroundColorTask)
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
                        if (toDoTask.getDone()) isDoneModifier(color = Color.White)
                        else inProgressModifier(
                            color = systemColor,
                            radius = 8.dp
                        )
                    )
                    .padding(8.dp),
                imageVector = ImageVector.vectorResource(id = toDoTask.taskType.icon),
                contentDescription = toDoTask.taskName,
                tint = if (toDoTask.getDone()) Neutral6 else systemColor
            )
            Text(
                text = toDoTask.taskName,
                style = VisbyTypography.subtitle2,
                color = if (toDoTask.getDone()) Neutral6 else Neutral3
            )
        }
        val taskTime = Date(toDoTask.time * 1000)
        val formatter = SimpleDateFormat("h:mm aa", Locale.ENGLISH)
        Text(
            text = formatter.format(taskTime).uppercase(),
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
    HomeTodoTaskItem(toDoTask = ToDoTask(), systemColor = SystemColor)
}

@Preview
@Composable
fun ToDoTaskDonePreview() {
    HomeTodoTaskItem(toDoTask = ToDoTask(), systemColor = SystemColor)
}