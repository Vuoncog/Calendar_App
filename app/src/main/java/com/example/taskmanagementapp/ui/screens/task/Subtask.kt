package com.example.taskmanagementapp.ui.screens.task

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskmanagementapp.R
import com.example.taskmanagementapp.constant.SystemColorSet
import com.example.taskmanagementapp.ui.theme.*

@Composable
fun Subtask(
    systemColor: Color
) {
    var subtaskQuantity by remember {
        mutableStateOf(0)
    }
    var isCheck by remember { mutableStateOf(false) }
    val addSubtask: () -> Unit = {
        subtaskQuantity++
    }
    val minusSubtask: () -> Unit = {
        subtaskQuantity--
    }
    val switchClicked: (Boolean) -> Unit = {
        isCheck = it
        if (!isCheck) subtaskQuantity = 0
    }

    Column(
        modifier = Modifier.padding(
            top = 8.dp,
            start = 16.dp,
            end = 16.dp
        ),
    ) {
        SubtaskHeader(
            isCheck = isCheck,
            onSwitchClicked = switchClicked,
            systemColor = systemColor
        )
        if (isCheck) {
            Column(
                modifier = Modifier.padding(
                    top = 8.dp,
                    bottom = 12.dp
                ),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                for (index in 1..subtaskQuantity) {
                    MiniSubtask(
                        onMinusSubtaskClicked = minusSubtask
                    )
                }
                AddSubtask(
                    onAddSubtaskClicked = addSubtask
                )
            }
        }

    }
}

@Composable
fun AddSubtask(
    onAddSubtaskClicked: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(
                start = 32.dp,
                end = 16.dp
            )
            .clickable {
                onAddSubtaskClicked()
            }
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_add),
            contentDescription = "Alarm",
            tint = Neutral1,
            modifier = Modifier.size(20.dp)
        )
        Text(
            text = "Add Subtask",
            style = VisbyTypography.body2
        )
    }
}

@Composable
fun SubtaskHeader(
    systemColor: Color = SystemColor,
    isCheck: Boolean = false,
    onSwitchClicked: (Boolean) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_subtasks),
            contentDescription = "Alarm",
            tint = systemColor
        )

        Text(
            text = "Subtasks",
            style = VisbyTypography.subtitle1,
            color = Neutral1,
            modifier = Modifier.weight(1f)
        )

        Switch(checked = isCheck, onCheckedChange = {
            onSwitchClicked(it)
        })
    }
}

@Composable
fun MiniSubtask(
    onMinusSubtaskClicked: () -> Unit
) {
    var textField by remember { mutableStateOf("") }
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(
                start = 32.dp,
                end = 16.dp
            )
    ) {
        SubtaskTick()
        BasicTextField(
            modifier = Modifier.weight(1f),
            value = textField,
            onValueChange = { textField = it },
            textStyle = TextStyle(
                color = Neutral1,
                fontStyle = VisbyTypography.body2.fontStyle,
                fontFamily = VisbyFontFamily,
                fontWeight = VisbyTypography.body2.fontWeight,
                letterSpacing = VisbyTypography.body2.letterSpacing,
                fontSize = VisbyTypography.body2.fontSize,
            ),
            decorationBox = { innerTextField ->
                Row(modifier = Modifier.fillMaxWidth()) {
                    if (textField.isEmpty()) {
                        Text(
                            text = "Subtask name",
                            color = Neutral4,
                            style = VisbyTypography.body2
                        )
                    }
                }
                innerTextField()
            }
        )
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_remove),
            contentDescription = "Remove",
            tint = Neutral3,
            modifier = Modifier
                .size(20.dp)
                .clickable {
                    onMinusSubtaskClicked()
                }
        )
    }
}

@Composable
fun SubtaskTick(
    isTicked: Boolean = false
) {
    var tick by remember { mutableStateOf(isTicked) }

    IconButton(
        onClick = {
            tick = !tick
        },
        modifier = Modifier
            .size(20.dp)
            .clip(CircleShape)
            .border(
                width = 1.dp,
                color = SystemColor,
                shape = CircleShape
            )
            .background(color = if (tick) Primary4 else Color.Transparent)
            .padding(0.dp)
    ) {
        Icon(
            modifier = Modifier.size(12.dp),
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_tick),
            contentDescription = "content description",
            tint = if (tick) Neutral8 else Color.Transparent,
        )
    }
}

@Preview
@Composable
fun SubtaskPreview() {
    Subtask(systemColor = SystemColorSet.ORANGE.primaryColor)
}