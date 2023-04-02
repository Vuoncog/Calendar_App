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
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskmanagementapp.R
import com.example.taskmanagementapp.ui.theme.*

@Composable
fun TaskInfoCard() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(color = BackgroundColorTask)
            .padding(12.dp)
            .fillMaxWidth()
    ) {
        TaskInfo()

        Image(
            painter = painterResource(id = R.drawable.hop),
            contentDescription = "Hop Image",
            modifier = Modifier.size(80.dp)
        )

        CircularProgress()
    }
}

@Composable
fun CircularProgress() {
    Box(
        modifier = Modifier.size(56.dp)
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(56.dp),
            progress = 0.25f,
            backgroundColor = Neutral7,
            color = SystemColor,
            strokeCap = StrokeCap.Round
        )

        Text(
            text = "25%",
            style = VisbyTypography.subtitle1,
            color = Primary4,
            modifier = Modifier.align(Center)
        )

    }
}

@Composable
fun TaskInfo(

) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "4 tasks",
            style = VisbyTypography.h6,
            color = SystemColor
        )

        Text(
            text = "Completed: 1",
            style = VisbyTypography.subtitle1,
            color = Neutral2
        )
    }
}

@Preview
@Composable
fun TaskInfoCardPreview() {
    TaskInfoCard()
}