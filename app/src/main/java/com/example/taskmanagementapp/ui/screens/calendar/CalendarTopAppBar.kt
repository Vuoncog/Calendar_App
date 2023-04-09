package com.example.taskmanagementapp.ui.screens.calendar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.example.taskmanagementapp.ui.theme.Neutral2
import com.example.taskmanagementapp.ui.theme.VisbyFontFamily

@Composable
fun CalendarTopAppBar() {
    CalendarAppBar()
}

@Composable
fun CalendarAppBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.calendar_top_background),
            contentDescription = "Image Top App Bar",
            contentScale = ContentScale.FillBounds
        )
    }

    TopAppBar(
        title = {
            Text(
                text = "Calendar",
                fontFamily = VisbyFontFamily,
                fontSize = MaterialTheme.typography.h6.fontSize,
                fontWeight = FontWeight.Medium,
            )
        },
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        actions = {
            SearchIcon()
        }
    )
}

@Composable
fun SearchIcon() {
    IconButton(onClick = { /*TODO*/ }) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_search),
            contentDescription = "Search Icon",
            tint = Neutral2
        )
    }
}

@Preview
@Composable
fun MemoTopAppBarPreview() {
    CalendarTopAppBar()
}