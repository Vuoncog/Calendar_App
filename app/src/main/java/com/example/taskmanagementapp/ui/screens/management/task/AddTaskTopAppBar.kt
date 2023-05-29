package com.example.taskmanagementapp.ui.screens.management.task

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
import androidx.compose.ui.unit.dp
import com.example.taskmanagementapp.R
import com.example.taskmanagementapp.data.SharedViewModel
import com.example.taskmanagementapp.ui.theme.SystemColor
import com.example.taskmanagementapp.ui.theme.VisbyFontFamily

@Composable
fun AddTaskTopAppBar(
    onBackClicked: () -> Unit,
    sharedViewModel: SharedViewModel,
    onFinished: () -> Unit
) {
    AddTaskAppBar(
        onBackClicked = onBackClicked,
        sharedViewModel = sharedViewModel,
        onFinished = onFinished
    )
}

@Composable
fun AddTaskAppBar(
    onBackClicked: () -> Unit,
    sharedViewModel: SharedViewModel,
    onFinished: () -> Unit
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
                text = "Add Event",
                fontFamily = VisbyFontFamily,
                fontSize = MaterialTheme.typography.h6.fontSize,
                fontWeight = FontWeight.Medium,
            )
        },
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        actions = {
            AddTaskCheckIcon(onClicked = {
                sharedViewModel.addEventInfo(onFinished)
            }
            )
        },
        navigationIcon = {
            AddTaskBackIcon(onBackClicked = onBackClicked)
        }
    )
}

@Composable
fun AddTaskBackIcon(
    systemColor: Color = SystemColor,
    onBackClicked: () -> Unit
) {
    IconButton(onClick = {
        onBackClicked()
    }) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_chevron_left),
            contentDescription = "Close icon",
            tint = systemColor,
            modifier = Modifier.size(32.dp)
        )
    }
}

@Composable
fun AddTaskCheckIcon(
    systemColor: Color = SystemColor,
    onClicked: () -> Unit
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

/*
@Preview
@Composable
fun AddTaskTopAppBarPreview() {
    AddTaskTopAppBar(
        onBackClicked = {}
    )
}*/
