package com.example.taskmanagementapp.ui.screens.management

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskmanagementapp.R
import com.example.taskmanagementapp.ui.screens.calendar.NextWeekIcon
import com.example.taskmanagementapp.ui.screens.calendar.PreviousWeekIcon
import com.example.taskmanagementapp.ui.theme.VisbyFontFamily
import java.util.*

@Composable
fun ManagementTopAppBar(
    onPrevWeekClicked: (Date) -> Unit,
    onNextWeekClicked: (Date) -> Unit,
    currentDay: Date
) {
    ManagementAppBar(
        onPrevWeekClicked = onPrevWeekClicked,
        onNextWeekClicked = onNextWeekClicked,
        currentDay = currentDay
    )
}

@Composable
fun ManagementAppBar(
    onPrevWeekClicked: (Date) -> Unit,
    onNextWeekClicked: (Date) -> Unit,
    currentDay: Date
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.management_background),
            contentDescription = "Image Top App Bar",
            contentScale = ContentScale.FillBounds
        )
    }

    TopAppBar(
        title = {
            Text(
                text = "Management",
                fontFamily = VisbyFontFamily,
                fontSize = MaterialTheme.typography.h6.fontSize,
                fontWeight = FontWeight.Medium,
            )
        },
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        actions = {
            NextWeekIcon(
                nextDayClicked = onNextWeekClicked,
                currentDay = currentDay
            )
        },
        navigationIcon = {
            PreviousWeekIcon(
                previousDayClicked = onPrevWeekClicked,
                currentDay = currentDay
            )
        }
    )
}

@Preview
@Composable
fun ManagementTopAppBarPreview() {
    ManagementTopAppBar(
        currentDay = Date(),
        onNextWeekClicked = {},
        onPrevWeekClicked = {}
    )
}