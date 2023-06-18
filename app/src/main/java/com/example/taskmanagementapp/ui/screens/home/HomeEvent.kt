package com.example.taskmanagementapp.ui.screens.home

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
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
import androidx.compose.ui.unit.dp
import com.example.taskmanagementapp.R
import com.example.taskmanagementapp.constant.EventInfo
import com.example.taskmanagementapp.ui.theme.Neutral2
import com.example.taskmanagementapp.ui.theme.VisbyTypography
import com.mrerror.singleRowCalendar.DateUtils
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Date

@Composable
fun HomeEvent(
    event: EventInfo,
    systemColor: Color,
    subSystemColor: Color,
    sticker: Int,
) {
    fun weekday(date: Date): String = DateUtils.getDay3LettersName(date)
    fun dayName(date: Date): String = DateUtils.getDayNumber(date)
    fun monthName(date: Date): String = DateUtils.getMonthName(date)
    fun year(date: Date): String = DateUtils.getYear(date)
    fun hour(date: Date): String = date.hours.toString()
    fun minute(date: Date): String = date.minutes.toString()
    fun dateFormatter(date: Date): String =
        "${weekday(date)}, ${dayName(date)} ${monthName(date)}, ${year(date)}"

    fun timeFormatter(date: Date): String = "${hour(date)}:${minute(date)}"
    val currentTime = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().epochSecond
    //if(event.startTime > currentTime){
        val eventDate = Date(event.startTime * 1000)
        Card(
            backgroundColor = subSystemColor,
            modifier = Modifier
                .padding(end = 16.dp)
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                .clip(RoundedCornerShape(size = 12.dp)),
            elevation = 0.dp
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 12.dp,),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            )
            {
                Text(
                    text = event.title,
                    style = VisbyTypography.h5,
                    maxLines = 2,
                    color = Neutral2
                )

                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    MiniDetail(
                        icon = R.drawable.ic_clock,
                        title = "${dateFormatter(eventDate)} - ${timeFormatter(eventDate)}",
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
                    painter = painterResource(id = sticker),
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
            color = Neutral2
        )
    }
}