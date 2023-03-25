package com.example.taskmanagementapp.ui.theme.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Alignment.Companion.BottomStart
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskmanagementapp.R
import com.example.taskmanagementapp.database.Note
import com.example.taskmanagementapp.ui.theme.*

@Composable
fun HomeContent() {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row {
            Text(
                text = "Monday",
                style = VisbyTypography.h6,
                color = Primary4
            )
            Text(
                text = ", 30 Nov",
                style = VisbyTypography.h6,
                color = Neutral1
            )
        }
        MainTask()

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Other tasks",
                style = VisbyTypography.h6,
                color = Neutral1
            )
            Text(
                text = ("1/4 DONE").uppercase(),
                style = VisbyTypography.button,
                modifier = Modifier.align(CenterVertically),
                color = Neutral5
            )
        }

        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)){
            items(getListNotes()){
                OtherTaskItem(note = it)
            }
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainTask() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .clip(RoundedCornerShape(size = 12.dp)),
        backgroundColor = BackgroundColorTask,
        elevation = 0.dp
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Card(
                    modifier = Modifier
                        .align(CenterVertically)
                        .clip(RoundedCornerShape(8.dp))
                        .background(color = Neutral8)
                ) {
                    Text(
                        text = "In progress",
                        color = Primary5,
                        style = VisbyTypography.caption,
                        modifier = Modifier.padding(8.dp)
                    )
                }
                Card(
                    modifier = Modifier
                        .align(CenterVertically),
                    backgroundColor = Color.Transparent,
                    elevation = 0.dp
                ) {
                    Deadline()
                }
            }
            Column()
            {
                Text(
                    text = "Todo App Mobile Design with Android Kotlin",
                    style = VisbyTypography.h5,
                    maxLines = 2
                )
                Text(
                    text = "The subcription",
                    style = VisbyTypography.body2,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Column {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    CompositionLocalProvider(LocalMinimumTouchTargetEnforcement provides false) {
                        Checkbox(
                            checked = true,
                            onCheckedChange = {},
                            modifier = Modifier
                                .padding(12.dp)
                                .align(CenterVertically),
                            colors = CheckboxDefaults.colors(
                                checkedColor = Primary5,
                                uncheckedColor = Purple700,
                                checkmarkColor = Neutral8,
                                disabledColor = Purple700,
                                disabledIndeterminateColor = Purple700
                            )
                        )
                    }
                    Text(
                        text = "Figma",
                        modifier = Modifier
                            .padding(start = 32.dp)
                            .align(CenterVertically),
                        style = VisbyTypography.subtitle1
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    CompositionLocalProvider(LocalMinimumTouchTargetEnforcement provides false) {
                        Checkbox(
                            checked = false,
                            onCheckedChange = {},
                            modifier = Modifier
                                .padding(12.dp)
                                .align(CenterVertically)
                        )
                    }
                    Text(
                        text = "Android Studio",
                        modifier = Modifier
                            .padding(start = 32.dp)
                            .align(CenterVertically),
                        style = VisbyTypography.subtitle1
                    )
                }
            }
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = BottomEnd
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

@Composable
fun Deadline() {
    Row() {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_flag_solid),
            contentDescription = "Deadline",
            tint = Neutral3
        )
        Text(
            text = "9:00 AM",
            modifier = Modifier
                .align(CenterVertically)
                .padding(start = 8.dp),
            style = VisbyTypography.subtitle2
        )
    }
}

@Composable
fun OtherTaskItem(note : Note) {
        Column(modifier = Modifier
            .border(
                1.dp,
                color = Primary4,
                shape = RoundedCornerShape(12.dp)
            )
            .fillMaxWidth()) {
            Text(
                text = note.title,
                maxLines = 1,
                modifier = Modifier.padding(start = 12.dp, end = 12.dp, top = 12.dp),
                style = VisbyTypography.h6
            )
            Text(
                text = note.description,
                modifier = Modifier.padding(start = 12.dp, top = 4.dp),
                maxLines = 1,
                style = VisbyTypography.body2
            )
            Row(modifier = Modifier.padding(start = 12.dp, top = 12.dp, bottom = 12.dp))
            {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_flag_solid),
                    contentDescription = "",
                    tint = Primary4
                )
                Text(
                    text = "Today, 8:00 AM - 9:00 AM",
                    modifier = Modifier.padding(start = 8.dp),
                    style = VisbyTypography.subtitle2
                )
            }
        }
}
fun getListNotes() : List<Note>
{
    val list = mutableListOf<Note>()
    list.add(Note("Title 1", "Description 1", false))
    list.add(Note("Title 2", "Description 2", false))
    return list
}
@Preview
@Composable
fun HomeContentPreview() {
    HomeContent()
}