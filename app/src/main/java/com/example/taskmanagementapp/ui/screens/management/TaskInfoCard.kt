package com.example.taskmanagementapp.ui.screens.management

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskmanagementapp.R
import com.example.taskmanagementapp.constant.TaskType
import com.example.taskmanagementapp.constant.ToDoTask
import com.example.taskmanagementapp.ui.theme.*

@Composable
fun TaskInfoCard(
    listTask: List<ToDoTask>
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(color = BackgroundColorTask)
            .padding(12.dp)
            .fillMaxWidth()
    ) {
        TaskInfo(
            listTask = listTask,
        )

        Image(
            painter = painterResource(id = R.drawable.cat_emoji_wink),
            contentDescription = "Hop Image",
            modifier = Modifier.size(64.dp)
        )

        CircularProgress(listTask = listTask)
    }
}

@Composable
fun CircularProgress(
    listTask: List<ToDoTask>
) {
    val percentProgress: (List<ToDoTask>) -> Float = { it ->
        var percentProgress = 0f
        it.forEach {
            if (it.isDone) percentProgress++
        }
        percentProgress / it.size
    }

    Box(
        modifier = Modifier.size(56.dp)
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(56.dp),
            progress = percentProgress(listTask),
            backgroundColor = Neutral7,
            color = SystemColor,
            strokeCap = StrokeCap.Round
        )

        Text(
            text = "${(percentProgress(listTask) * 100).toInt()}%",
            style = VisbyTypography.subtitle1,
            color = Primary4,
            modifier = Modifier.align(Center)
        )

    }
}

@Composable
fun TaskInfo(
    listTask: List<ToDoTask>,
) {
    val tasksCompleted: (List<ToDoTask>) -> Int = { it ->
        var taskCompleted: Int = 0
        it.forEach {
            if (it.isDone) taskCompleted++
        }
        taskCompleted
    }
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "${listTask.size} tasks",
            style = VisbyTypography.h6,
            color = SystemColor
        )

        Text(
            text = "Completed: ${tasksCompleted(listTask)}",
            style = VisbyTypography.subtitle1,
            color = Neutral2
        )
    }
}

@Preview
@Composable
fun TaskInfoCardPreview() {
    TaskInfoCard(
        listTask = listOf(
            ToDoTask(TaskType.Running, "Walking", false),
            ToDoTask(TaskType.Shopping, "Go shopping", true),
            ToDoTask(TaskType.Running, "Walking", true)
        )
    )
}