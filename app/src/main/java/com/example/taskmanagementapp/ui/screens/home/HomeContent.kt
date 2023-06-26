@file:Suppress("SENSELESS_COMPARISON")

package com.example.taskmanagementapp.ui.screens.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskmanagementapp.R
import com.example.taskmanagementapp.constant.*
import com.example.taskmanagementapp.data.SharedViewModel
import com.example.taskmanagementapp.ui.theme.Neutral1
import com.example.taskmanagementapp.ui.theme.Neutral3
import com.example.taskmanagementapp.ui.theme.VisbyTypography
import java.time.LocalDate
import java.util.*

@Composable
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
fun HomeContent(
    listAllTask: List<ToDoTask>,
    listAllEvent: List<EventInfo>,
    currentDate: Date,
    systemColorSet: SystemColorSet = SystemColorSet.ORANGE,
    sharedViewModel: SharedViewModel
) {
    LaunchedEffect(key1 = true, block = {
        sharedViewModel.dateOfEvent = LocalDate.now().toEpochDay()
        sharedViewModel.dateOfTask = LocalDate.now().toEpochDay()
        sharedViewModel.getEventInfo()
        sharedViewModel.getToDoTask()
        Log.e("SCREEN", "HOME")
    })
    Scaffold(topBar = { HomeAppBar(sharedViewModel = sharedViewModel) }
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            if (listAllTask.isEmpty()) {
                NoToDoTask(
                    systemColor = systemColorSet.primaryColor,
                    subSystemColor = systemColorSet.secondaryColor,
                    sticker = R.drawable.bunny_emoji_sad
                )
            } else {
                ExistTaskText(
                    list = listAllTask,
                    systemColor = systemColorSet.primaryColor
                )
                TodoTask(
                    list = listAllTask,
                    systemColor = systemColorSet.primaryColor,
                    subSystemColor = systemColorSet.secondaryColor,
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(top = 12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text(
                    text = stringResource(R.string.upcoming_events),
                    style = VisbyTypography.subtitle1,
                    color = Neutral3,
                    fontWeight = FontWeight.SemiBold,
                    letterSpacing = 1.sp
                )
                if (listAllEvent.isEmpty()) {
                    NoEventCard(
                        currentDate = currentDate,
                        systemColor = systemColorSet.primaryColor,
                        subSystemColor = systemColorSet.secondaryColor,
                        sticker = R.drawable.bunny_emoji_sad
                    )
                } else {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier
                            .verticalScroll(state = rememberScrollState())
                            .padding(bottom = 50.dp)
                    )
                    {
                        listAllEvent.forEach { event ->
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
}

@Composable
fun ExistTaskText(
    list: List<ToDoTask>,
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