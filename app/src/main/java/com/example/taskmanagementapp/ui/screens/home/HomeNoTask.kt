package com.example.taskmanagementapp.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskmanagementapp.R
import com.example.taskmanagementapp.ui.theme.*
import com.mrerror.singleRowCalendar.DateUtils
import java.util.*

@Composable
fun HomeNoTask(
    systemColor: Color = SystemColor,
    subSystemColor: Color = BackgroundColorTask,
    sticker: Int,
    currentDate: Date
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        NoTaskText(
            systemColor = systemColor
        )

        NoTaskPlanned(
            systemColor = systemColor,
            subSystemColor = subSystemColor,
            sticker = sticker
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(top = 12.dp)
        ) {
            Text(
                text = stringResource(R.string.upcoming_events),
                style = VisbyTypography.subtitle1,
                color = Neutral3,
                fontWeight = FontWeight.SemiBold,
                letterSpacing = 1.sp
            )
            NoEventCard(
                currentDate = currentDate,
                sticker = sticker
            )
        }
    }
}

@Composable
fun NoTaskText(
    systemColor: Color = SystemColor
) {
    Text(
        buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = Neutral1,
                    fontStyle = VisbyTypography.h6.fontStyle,
                    fontWeight = VisbyTypography.h3.fontWeight,
                    fontSize = VisbyTypography.h6.fontSize,
                    letterSpacing = VisbyTypography.h6.letterSpacing
                )
            ) {
                append("Today, you don’t have any ")
            }
            withStyle(
                style = SpanStyle(
                    color = systemColor,
                    fontStyle = VisbyTypography.h6.fontStyle,
                    fontWeight = VisbyTypography.h3.fontWeight,
                    fontSize = VisbyTypography.h6.fontSize,
                    letterSpacing = VisbyTypography.h6.letterSpacing
                )
            ) {
                append("tasks")
            }
        }
    )
}

@Composable
fun NoTaskPlanned(
    systemColor: Color,
    subSystemColor: Color,
    sticker: Int
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "No task planned".uppercase(),
                style = VisbyTypography.subtitle2,
                color = Neutral5,
                modifier = Modifier.weight(1f)
            )
            Text(text = stringResource(R.string.view_all),
                style = VisbyTypography.button,
                color = systemColor,
                modifier = Modifier.clickable {

                })
        }

        NoTaskCard(
            systemColor = systemColor,
            subSystemColor = subSystemColor,
            sticker = sticker
        )
    }
}

@Composable
fun NoTaskCard(
    systemColor: Color = SystemColor,
    subSystemColor: Color = BackgroundColorTask,
    sticker: Int = R.drawable.boar_emoji_sad
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .border(
                width = 1.dp,
                color = systemColor,
                shape = RoundedCornerShape(12.dp)
            )
            .clip(RoundedCornerShape(12.dp))
            .background(subSystemColor)
            .padding(8.dp)
    ) {
        Image(
            modifier = Modifier.size(40.dp),
            painter = painterResource(id = sticker),
            contentDescription = "Cat sticker",
        )
        Text(
            text = "No task",
            style = VisbyTypography.subtitle2,
            color = Neutral3,
            maxLines = 2,
            modifier = Modifier.padding(end = 4.dp)
        )
    }
}

@Composable
fun NoEventCard(
    systemColor: Color = SystemColor,
    subSystemColor: Color = BackgroundColorTask,
    currentDate: Date,
    sticker: Int
) {
    val dateFormatter = "${DateUtils.getDayName(currentDate)}, " +
            "${DateUtils.getDayNumber(currentDate)} ${DateUtils.getMonth3LettersName(currentDate)}"

    Card(
        backgroundColor = subSystemColor,
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = systemColor,
                shape = RoundedCornerShape(12.dp)
            )
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
                text = "You have no events today",
                style = VisbyTypography.h6,
                maxLines = 2,
                color = Neutral2
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                MiniDetail(
                    icon = R.drawable.ic_detail,
                    title = dateFormatter,
                    systemColor = systemColor
                )
                MiniDetail(
                    icon = R.drawable.ic_add,
                    title = "Click here to add event",
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
}

@Preview
@Composable
fun HomeNoTaskPreview() {
    HomeNoTask(
        currentDate = Calendar.getInstance().time,
        sticker = R.drawable.boar_emoji_sad
    )
}