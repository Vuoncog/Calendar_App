package com.example.taskmanagementapp.ui.theme.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskmanagementapp.R
import com.example.taskmanagementapp.ui.theme.*

@Composable
fun HomeContent() {
    Box(modifier = Modifier.padding(horizontal = 16.dp)) {
        MainTask()
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainTask() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(size = 12.dp)),
        backgroundColor = BackgroundColorTask, elevation = 0.dp
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

@Preview
@Composable
fun HomeContentPreview() {
    MainTask()
}