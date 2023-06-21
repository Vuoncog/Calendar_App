package com.example.taskmanagementapp.ui.screens.calendar

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.taskmanagementapp.R
import com.example.taskmanagementapp.constant.EventInfo
import com.example.taskmanagementapp.constant.SubScreen
import com.example.taskmanagementapp.data.SharedViewModel
import com.example.taskmanagementapp.ui.theme.Neutral2
import com.example.taskmanagementapp.ui.theme.VisbyTypography
import com.google.gson.Gson


@Composable
fun CalendarEvent(
    listEvent: List<EventInfo>,
    listOffset: List<Float>,
    listSpace: List<Float>,
    state: ScrollState,
    height: Dp,
    offset: Dp,
    sharedViewModel: SharedViewModel,
    navController : NavHostController
) {
    Box(
        modifier = Modifier
            .height(height)
            .offset(y = offset + 20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .verticalScroll(
                    state = state,
                )
                .padding(
                    start = 72.dp,
                    end = 16.dp,
                    top = 20.dp
                ),
            verticalArrangement = Arrangement.Top
        ) {
            var sumOffset = listOffset[0]
            var paddingSpace = 0
            for (index in listEvent.indices) {
                sumOffset += listOffset[index]
                var startTime = sharedViewModel.getHourAndMinute(listEvent[index].startTime)
                val endTime  = sharedViewModel.getHourAndMinute(listEvent[index].endTime)
                if(startTime > endTime) startTime -= 24
                if (startTime < listSpace[index]
                    && listSpace[index] < endTime
                ) {
                    paddingSpace += 12
                } else {
                    paddingSpace = 0
                }
                EventCard(
                    eventInfo = listEvent[index],
                    offset = sumOffset.dp,
                    paddingSpace = paddingSpace.dp,
                    sharedViewModel = sharedViewModel,
                    navController = navController
                )
            }
            Box(
                modifier = Modifier
                    .height(height)
                    .background(color = Color.Transparent)
            )
        }
    }
}

@Composable
fun EventCard(
    eventInfo: EventInfo,
    offset: Dp,
    paddingSpace: Dp,
    sharedViewModel: SharedViewModel,
    navController: NavHostController
) {
    var startTime = sharedViewModel.getHourAndMinute(eventInfo.startTime)
    val endTime  = sharedViewModel.getHourAndMinute(eventInfo.endTime)
    if(startTime > endTime) startTime -= 24
    val timeRange = endTime - startTime
    val heightEvent = heightEvent(timeRange)
    val offsetEvent = offsetEvent(startTime)
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(eventInfo.color.toULong())
        ),
        modifier = Modifier
            .offset(y = -(offset))
            .offset(y = offsetEvent.dp)
            .height(heightEvent.dp)
            .fillMaxWidth()
            .padding(end = paddingSpace)
            .clickable {
                val eventString = Gson().toJson(eventInfo)
                navController.navigate("${SubScreen.EventDetail.route}/${eventString}")
            }
    ) {
        Row {
            Text(
                text = eventInfo.title,
                style = VisbyTypography.subtitle1,
                color = Neutral2,
                fontWeight = FontWeight.SemiBold,
                fontSize = if (timeRange >= 1f)
                    VisbyTypography.subtitle1.fontSize
                else (24 * timeRange).sp,
                modifier = Modifier
                    .padding(
                        horizontal = 16.dp,
                        vertical = if (timeRange >= 1f) 12.dp
                        else (4 * timeRange).dp
                    )
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                if (timeRange > 1.5f) {
                    Image(
                        painter = painterResource(id = R.drawable.hop),
                        contentDescription = "Hop Image",
                        modifier = Modifier
                            .size(80.dp)
                            .align(Alignment.TopEnd)
                            .rotate(-12.59f)
                            .offset(
                                x = 12.dp,
//                                y = 12.dp
                            )
                    )
                }
            }

        }

    }
}

fun heightEvent(timeRange: Float): Float = 56 * timeRange
fun offsetEvent(startTime: Float): Float = 56 * startTime
