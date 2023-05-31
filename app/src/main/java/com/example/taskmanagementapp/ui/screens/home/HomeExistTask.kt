package com.example.taskmanagementapp.ui.screens.home

import androidx.compose.foundation.background
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
import com.example.taskmanagementapp.constant.EventInfo
import com.example.taskmanagementapp.constant.SystemColorSet
import com.example.taskmanagementapp.constant.TaskType
import com.example.taskmanagementapp.database.Note
import com.example.taskmanagementapp.ui.theme.Neutral1
import com.example.taskmanagementapp.ui.theme.Neutral3
import com.example.taskmanagementapp.ui.theme.SystemColor
import com.example.taskmanagementapp.ui.theme.VisbyTypography
import java.util.*

@Composable
fun HomeExistTask(
    listTask: Note,
    systemColorSet: SystemColorSet
) {
    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(
                top = 16.dp,
                start = 16.dp,
                bottom = 16.dp
            )
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Column {
            Text(
                text = stringResource(R.string.home_first_title),
                style = VisbyTypography.h6,
                color = Neutral1
            )

            Text(
                text = "4 todo tasks",
                style = VisbyTypography.h6,
                color = SystemColor
            )
        }

        listTask.listToDoTask.let {
            TodoTask(
                list = it,
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
            listTask.listEvent.forEach { eventInfo ->
                HomeEvent(
                    event = eventInfo,
                    systemColor = systemColorSet.primaryColor,
                    subSystemColor = systemColorSet.secondaryColor,
                    sticker = systemColorSet.listStickerSet[0]
                )
            }

        }

    }
}

@Composable
@Preview
fun HomeContentPreview() {
    HomeExistTask(
        listTask = Note(
            date = Calendar.getInstance().time,
            listToDoTask = listOf(TaskType.Running),
            listEvent = listOf(
                EventInfo(
                    color = 0xFF964908,
                    title = "UIT",
                    startTime = 9f,
                    endTime = 2f,
                )
            )
        ),
        systemColorSet = SystemColorSet.ORANGE
    )

}