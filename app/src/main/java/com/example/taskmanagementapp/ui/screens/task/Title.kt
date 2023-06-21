package com.example.taskmanagementapp.ui.screens.task

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskmanagementapp.R
import com.example.taskmanagementapp.data.SharedViewModel
import com.example.taskmanagementapp.ui.theme.*


@Composable
fun Title(
    systemColor: Color = SystemColor,
    subSystemColor: Color,
    isEvent: Boolean,
    titleAndDetail: Pair<String, String>? = null,
    sharedViewModel: SharedViewModel? = null
): Pair<String, String> {
    var title by remember { mutableStateOf(if (titleAndDetail != null) titleAndDetail.first else "") }
    var detail by remember { mutableStateOf(if (titleAndDetail != null) titleAndDetail.second else "") }
    val sticker = remember { mutableStateOf(if(sharedViewModel?.oldTaskInfo!!.taskType.icon == 0) R.drawable.ic_ban else sharedViewModel.oldTaskInfo.taskType.icon)}
    sharedViewModel?.taskSticker = sticker.value

    Row(
        modifier = Modifier
            .padding(
                horizontal = 16.dp,
                vertical = 12.dp
            )
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFFFBFBFB))
            .border(
                width = 1.dp,
                color = systemColor,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(
                vertical = 16.dp,
                horizontal = 12.dp
            )
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.weight(1f)
        ) {
            BasicTextField(
                value = title,
                onValueChange = { title = it },
                modifier = Modifier
                    .fillMaxWidth(),
                textStyle = VisbyTypography.subtitle1,
                maxLines = 1,
                decorationBox = { innerTextField ->
                    Row(modifier = Modifier.fillMaxWidth()) {
                        if (title.isEmpty()) {
                            Text(
                                text = "Title",
                                color = Neutral4,
                                style = VisbyTypography.subtitle1,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                    innerTextField()
                }
            )
            BasicTextField(
                value = detail,
                onValueChange = {
                    detail = it
                },
                modifier = Modifier.fillMaxWidth(),
                textStyle = VisbyTypography.overline,
                maxLines = 3,
                decorationBox = { innerTextField ->
                    Row(modifier = Modifier.fillMaxWidth()) {
                        if (detail.isEmpty()) {
                            Text(
                                text = "Add detail",
                                color = Neutral4,
                                style = VisbyTypography.overline,
                                fontWeight = FontWeight.Normal
                            )
                        }
                    }
                    innerTextField()
                }
            )
        }

        ChooseSticker(
            systemColor = systemColor,
            subSystemColor = subSystemColor,
            sticker = sticker,
            onIconClicked = {
                sticker.value = it
            }
        )

    }
    return Pair(title, detail)
}

@Composable
fun ChooseSticker(
    systemColor: Color,
    subSystemColor: Color,
    isEvent: Boolean,
    @DrawableRes sticker: MutableState<Int>,
    onIconClicked: (Int) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(3.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Sticker(
            isEvent = isEvent,
            systemColor = systemColor,
            subSystemColor = subSystemColor,
            sticker = sticker,
            onIconClicked = onIconClicked
        )
        Text(
            text = "Sticker",
            style = VisbyTypography.overline,
            color = Neutral1
        )
    }
}

@Preview
@Composable
fun TitlePreview() {
    Title(
        subSystemColor = BackgroundColorTask,
        isEvent = true
    )
}