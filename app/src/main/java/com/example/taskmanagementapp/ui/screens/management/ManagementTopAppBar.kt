package com.example.taskmanagementapp.ui.screens.management

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskmanagementapp.R
import com.example.taskmanagementapp.ui.theme.Neutral1
import com.example.taskmanagementapp.ui.theme.VisbyFontFamily
import java.time.LocalDate

@Composable
fun ManagementTopAppBar(
    currentDay: LocalDate,
    previousDayClicked: (LocalDate) -> Unit,
    nextDayClicked: (LocalDate) -> Unit,
) {
    ManagementAppBar(
        currentDay = currentDay,
        previousDayClicked = previousDayClicked,
        nextDayClicked = nextDayClicked,
    )
}

@Composable
fun ManagementAppBar(
    currentDay: LocalDate,
    previousDayClicked: (LocalDate) -> Unit,
    nextDayClicked: (LocalDate) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.memo_background),
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
            NextDayIcon(
                nextDayClicked = nextDayClicked,
                currentDay = currentDay
            )
        },
        navigationIcon = {
            PreviousDayIcon(
                previousDayClicked = previousDayClicked,
                currentDay = currentDay
            )
        }
    )
}

@Composable
fun PreviousDayIcon(
    previousDayClicked: (LocalDate) -> Unit,
    currentDay: LocalDate
) {
    IconButton(
        onClick = {
            previousDayClicked(currentDay)
        }
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_chevron_left),
            contentDescription = "Previous day",
            tint = Neutral1
        )
    }
}

@Composable
fun NextDayIcon(
    nextDayClicked: (LocalDate) -> Unit,
    currentDay: LocalDate
) {
    IconButton(
        onClick = {
            nextDayClicked(currentDay)
        }
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_chevron_right),
            contentDescription = "Next day",
            tint = Neutral1
        )
    }
}

@Preview
@Composable
fun ManagementTopAppBarPreview() {
    ManagementTopAppBar(
        currentDay = LocalDate.now(),
        previousDayClicked = {},
        nextDayClicked = {}
    )
}