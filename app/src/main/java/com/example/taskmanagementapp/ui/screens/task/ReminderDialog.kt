package com.example.taskmanagementapp.ui.screens.task

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.taskmanagementapp.ui.theme.Neutral1
import com.example.taskmanagementapp.ui.theme.VisbyTypography

@Composable
fun ReminderDialog(
    openDialogCustom: MutableState<Boolean>,
    reminderMinute: MutableState<Int>,
) {
    Dialog(
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        ),
        onDismissRequest = {
            openDialogCustom.value = !openDialogCustom.value
        }) {
        ReminderDialogUI(
            openDialogCustom = openDialogCustom,
            reminderMinute = reminderMinute
        )
    }
}

@Composable
fun ReminderDialogUI(
    openDialogCustom: MutableState<Boolean>,
    reminderMinute: MutableState<Int>,
) {
    val reminderList = listOf(5, 10, 15, 60, 2400)
    Column(
        modifier = Modifier
            .padding(horizontal = 32.dp)
            .clip(RoundedCornerShape(12.dp))
    ) {
        reminderList.forEach {
            Text(
                text = ReminderText(it),
                style = VisbyTypography.body2,
                color = Neutral1,
                modifier = Modifier
                    .clickable {
                        openDialogCustom.value = !openDialogCustom.value
                        reminderMinute.value = it
                    }
                    .background(Color.White)
                    .padding(
                        horizontal = 24.dp,
                        vertical = 12.dp
                    )
                    .fillMaxWidth()

            )

        }
    }
}

internal fun ReminderText(minute: Int): String {
    if (minute >= 2400) {
        return "${minute / 2400} day before"
    }
    if (minute >= 60) {
        return "${minute / 60} hour before"
    }
    return "$minute minutes before"
}