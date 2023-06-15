@file:Suppress("SENSELESS_COMPARISON")

package com.example.taskmanagementapp.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskmanagementapp.R
import com.example.taskmanagementapp.constant.RequestState
import com.example.taskmanagementapp.constant.SystemColorSet
import com.example.taskmanagementapp.constant.TaskType
import com.example.taskmanagementapp.database.Note
import com.example.taskmanagementapp.ui.theme.Neutral1
import com.example.taskmanagementapp.ui.theme.Neutral3
import com.example.taskmanagementapp.ui.theme.VisbyTypography
import java.util.*

@Composable
fun HomeContent(
    listAllTask: RequestState<Note>,
    currentDate: Date,
    systemColorSet: SystemColorSet = SystemColorSet.ORANGE
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        if (listAllTask is RequestState.Success) {
            if (listAllTask.data.listToDoTask.isEmpty()) {
                NoToDoTask(
                    systemColor = systemColorSet.primaryColor,
                    subSystemColor = systemColorSet.secondaryColor,
                    sticker = systemColorSet.listStickerSet[11]
                )
            } else {
                ExistTaskText(
                    list = listAllTask.data.listToDoTask,
                    systemColor = systemColorSet.primaryColor
                )
                TodoTask(
                    list = listAllTask.data.listToDoTask,
                    systemColor = systemColorSet.primaryColor
                )
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(top = 12.dp)
            ) {
                Text(
                    text = stringResource(R.string.upcoming_events),
                    style = VisbyTypography.subtitle1,
                    color = Neutral3,
                    fontWeight = FontWeight.SemiBold,
                    letterSpacing = 1.sp
                )
                if (listAllTask.data.listEvent.isEmpty()) {
                    NoEventCard(
                        currentDate = currentDate,
                        systemColor = systemColorSet.primaryColor,
                        subSystemColor = systemColorSet.secondaryColor,
                        sticker = systemColorSet.listStickerSet[5]
                    )
                } else {
                    listAllTask.data.listEvent.forEach { event ->
                        HomeEvent(
                            event = event,
                            systemColor = systemColorSet.primaryColor,
                            subSystemColor = systemColorSet.secondaryColor,
                            sticker = systemColorSet.listStickerSet[17]
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ExistTaskText(
    list: List<TaskType>,
    systemColor: Color
) {
    Column {
        Text(
            text = stringResource(R.string.home_first_title),
            style = VisbyTypography.h6,
            color = Neutral1
        )

        Text(
            text = "${list.size} todo tasks",
            style = VisbyTypography.h6,
            color = systemColor
        )
    }
}

@Composable
fun NoToDoTask(
    systemColor: Color,
    subSystemColor: Color,
    sticker: Int
) {
    NoTaskText(
        systemColor = systemColor
    )

    NoTaskPlanned(
        systemColor = systemColor,
        subSystemColor = subSystemColor,
        sticker = sticker
    )
}

@Preview
@Composable
fun HomePreview() {
    /*val listEvent = listOf(
        EventInfo(
            color = SystemColor,
            eventName = "UIT",
            startTime = 9f,
            timeRange = 2f,
        )
    )*/
    /*val listToDoTask = listOf(TaskType.Running)
    HomeContent(
        listAllTask = RequestState.Success(
            Note(
                date = Calendar.getInstance().time,
                listToDoTask = listToDoTask,
                listEvent = emptyList()
            )
        ),
        currentDate = Calendar.getInstance().time
    )*/
}