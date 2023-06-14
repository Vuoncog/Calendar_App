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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskmanagementapp.constant.SystemColorSet
import com.example.taskmanagementapp.constant.TaskType
import com.example.taskmanagementapp.constant.ToDoTask
import com.example.taskmanagementapp.ui.theme.Neutral2
import com.example.taskmanagementapp.ui.theme.Neutral7
import com.example.taskmanagementapp.ui.theme.VisbyTypography

@Composable
fun TaskInfoCard(
    systemColor: Color,
    subSystemColor: Color,
    listSticker: List<Int>,
    listTask: List<ToDoTask>,
) {
    if(listTask.size > 0){
        val percentProgress: (List<ToDoTask>) -> Float = { it ->
            var percentProgress = 0f
            it.forEach {
                if (it.getDone()) percentProgress++
            }
            percentProgress / it.size
        }

        val sticker: (Float) -> Int = {
            if (it < 0.25f) {
                listSticker[0]
            } else if (it < 0.5f) {
                listSticker[1]
            } else if (it < 0.75f) {
                listSticker[2]
            } else {
                listSticker[3]
            }
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(color = subSystemColor)
                .padding(12.dp)
                .fillMaxWidth()
        ) {
            TaskInfo(
                listTask = listTask,
                systemColor = systemColor,
            )

            Image(
                painter = painterResource(id = sticker(percentProgress(listTask))),
                contentDescription = "Hop Image",
                modifier = Modifier.size(64.dp)
            )

            CircularProgress(
                listTask = listTask,
                systemColor = systemColor,
                progress = percentProgress(listTask),
            )
        }
    }
}

@Composable
fun CircularProgress(
    listTask: List<ToDoTask>,
    systemColor: Color,
    progress: Float,
) {
//    val percentProgress: (List<ToDoTask>) -> Float = { it ->
//        var percentProgress = 0f
//        it.forEach {
//            if (it.isDone) percentProgress++
//        }
//        percentProgress / it.size
//    }

    Box(
        modifier = Modifier.size(56.dp)
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(56.dp),
            progress = progress,
            backgroundColor = Neutral7,
            color = systemColor,
            strokeCap = StrokeCap.Round
        )

        Text(
            text = "${(progress * 100).toInt()}%",
            style = VisbyTypography.subtitle1,
            color = systemColor,
            modifier = Modifier.align(Center)
        )

    }
}

@Composable
fun TaskInfo(
    listTask: List<ToDoTask>,
    systemColor: Color,
) {
    val tasksCompleted: (List<ToDoTask>) -> Int = { it ->
        var taskCompleted = 0
        it.forEach {
            if (it.getDone()) taskCompleted++
        }
        taskCompleted
    }
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "${listTask.size} tasks",
            style = VisbyTypography.h6,
            color = systemColor
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
            ToDoTask(taskType = TaskType.Running, taskName = "Walking", isDone = true, time = 0L),
            ToDoTask(taskType = TaskType.Shopping, taskName = "Go shopping", isDone = true, time = 0L),
            ToDoTask(taskType = TaskType.Running, taskName = "Walking", isDone = true, time = 0L)
        ),
        systemColor = SystemColorSet.ORANGE.primaryColor,
        subSystemColor = SystemColorSet.ORANGE.secondaryColor,
        listSticker = listOf(
            SystemColorSet.ORANGE.listStickerSet[5],
            SystemColorSet.ORANGE.listStickerSet[9],
            SystemColorSet.ORANGE.listStickerSet[6],
            SystemColorSet.ORANGE.listStickerSet[4],
        )
    )
}