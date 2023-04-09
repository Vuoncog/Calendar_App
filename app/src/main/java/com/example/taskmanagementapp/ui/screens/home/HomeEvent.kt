package com.example.taskmanagementapp.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.taskmanagementapp.R
import com.example.taskmanagementapp.ui.theme.BackgroundColorTask
import com.example.taskmanagementapp.ui.theme.Neutral2
import com.example.taskmanagementapp.ui.theme.VisbyTypography

@Composable
fun HomeEvent() {
    Card(
        backgroundColor = BackgroundColorTask,
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .clip(RoundedCornerShape(size = 12.dp)),
        elevation = 0.dp
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        )
        {
            Text(
                text = "Develop app mobile with Kotlin",
                style = VisbyTypography.h5,
                maxLines = 2,
                color = Neutral2
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                MiniDetail(icon = R.drawable.ic_clock, title = "9:00 AM")
                MiniDetail(icon = R.drawable.ic_note, title = "The subscription")
            }

        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomEnd
        ) {
            Image(
                modifier = Modifier
                    .size(100.dp)
                    .rotate(-12.59f)
                    .offset(x = 18.dp, y = 16.dp),
                painter = painterResource(id = R.drawable.hop),
                contentDescription = "Hop Image",
            )
        }
    }
}