package com.example.taskmanagementapp.ui.screens.task

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.taskmanagementapp.R
import com.example.taskmanagementapp.constant.EventInfo
import com.example.taskmanagementapp.data.SharedViewModel
import com.example.taskmanagementapp.ui.theme.*
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.launch

@Composable
fun AddTaskTopAppBar(
    onBackClicked: () -> Unit,
    sharedViewModel: SharedViewModel,
    onFinished: () -> Unit,
    isEvent: Boolean = false,
    isUpdateEvent: Boolean,
    systemColor: Color
) {
    AddTaskAppBar(
        onBackClicked = onBackClicked,
        sharedViewModel = sharedViewModel,
        onFinished = onFinished,
        isEvent = isEvent,
        isUpdateEvent = isUpdateEvent,
        systemColor = systemColor
    )
}

@OptIn(DelicateCoroutinesApi::class)
@Composable
fun AddTaskAppBar(
    onBackClicked: () -> Unit,
    sharedViewModel: SharedViewModel,
    onFinished: () -> Unit,
    isEvent: Boolean,
    isUpdateEvent: Boolean,
    systemColor: Color
) {
    val coroutinesScope = rememberCoroutineScope()
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
    ) {
        Image(
            modifier = Modifier.fillMaxSize(), painter = painterResource(
                id = if (isEvent) R.drawable.calendar_top_background
                else R.drawable.management_background
            ), contentDescription = "Image Top App Bar", contentScale = ContentScale.FillBounds
        )
    }

    TopAppBar(title = {
        Text(
            text = if (isEvent) {
                if (isUpdateEvent) "Update Event" else "Add Event"
            } else "Add Todo Task",
            fontFamily = VisbyFontFamily,
            fontSize = MaterialTheme.typography.h6.fontSize,
            fontWeight = FontWeight.Medium,
        )
    }, backgroundColor = Color.Transparent, elevation = 0.dp, actions = {
        if (isUpdateEvent) {
                RemoveIcon(
                    systemColor = systemColor,
                    onClicked = { coroutinesScope.launch { sharedViewModel.removeEvent(onFinished = onFinished) } },
                    sharedViewModel = sharedViewModel
                )
        }
        AddTaskCheckIcon(onClicked = {
            if (isUpdateEvent) {
                coroutinesScope.launch {
                    sharedViewModel.updateEventInfo(
                        EventInfo(
                            title = sharedViewModel.titleAndDetailEvent.value.first,
                            detail = sharedViewModel.titleAndDetailEvent.value.second,
                            startTime = sharedViewModel.startAndEndEvent.value.first,
                            endTime = sharedViewModel.startAndEndEvent.value.second,
                            color = sharedViewModel.oldEventInfo.color
                        ), onFinished = onFinished
                    )
                }
            } else {
                coroutinesScope.launch { sharedViewModel.addEventInfo(onFinished) }
            }
        })
    }, navigationIcon = {
        AddTaskBackIcon(onBackClicked = onBackClicked)
    })
}

@Composable
fun AddTaskBackIcon(
    systemColor: Color = SystemColor,
    onBackClicked: () -> Unit,
    size: Dp = 32.dp
) {
    IconButton(onClick = {
        onBackClicked()
    }) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_chevron_left),
            contentDescription = "Close icon",
            tint = systemColor,
            modifier = Modifier.size(size)
        )
    }
}

@Composable
fun AddTaskCheckIcon(
    systemColor: Color = SystemColor, onClicked: () -> Unit
) {
    IconButton(onClick = {
        onClicked()
    }) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_tick),
            contentDescription = "Close icon",
            tint = systemColor,
            modifier = Modifier.size(32.dp)
        )
    }
}
