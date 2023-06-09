package com.example.taskmanagementapp.ui.screens.home

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.taskmanagementapp.R
import com.example.taskmanagementapp.constant.EventInfo
import com.example.taskmanagementapp.ui.theme.Neutral2
import com.example.taskmanagementapp.ui.theme.VisbyTypography
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

@Composable
fun HomeEvent(
    event: EventInfo,
    systemColor: Color,
    subSystemColor: Color,
    sticker: Int,
) {
    val formatter = SimpleDateFormat("HH:mm", Locale.ENGLISH)
    val currentTime = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().epochSecond
    //if(event.startTime > currentTime){
    val eventDate = Date(event.startTime * 1000)
    Card(
        backgroundColor = subSystemColor,
        modifier = Modifier
            .border(
                width = 1.dp,
                color = systemColor,
                shape = RoundedCornerShape(12.dp)
            )
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .clip(RoundedCornerShape(size = 12.dp)),
        elevation = 0.dp
    ) {
        Column(
            modifier = Modifier
                .padding(
                    horizontal = 16.dp,
                    vertical = 12.dp
                ),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        )
        {
            Text(
                text = event.title,
                style = VisbyTypography.h6,
                maxLines = 2,
                color = Neutral2,
                fontWeight = FontWeight.SemiBold
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                MiniDetail(
                    icon = R.drawable.ic_clock,
                    title = formatter.format(eventDate),
                    systemColor = systemColor
                )
                MiniDetail(
                    icon = R.drawable.ic_note,
                    title = event.detail.toString(),
                    systemColor = systemColor
                )
            }

        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomEnd
        ) {
            Image(
                modifier = Modifier
                    .size(80.dp)
                    .offset(x = (-3).dp, y = 0.dp),
                imageVector = ImageVector.vectorResource(id = event.sticker),
                contentDescription = "Sticker",
            )
        }
    }
    //}
}

@Composable
fun MiniDetail(
    @DrawableRes icon: Int,
    title: String,
    systemColor: Color
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = icon),
            contentDescription = "Icon Detail",
            tint = systemColor,
            modifier = Modifier.size(20.dp)
        )

        Text(
            text = title,
            style = VisbyTypography.subtitle2,
            color = Neutral2,
        )
    }
}