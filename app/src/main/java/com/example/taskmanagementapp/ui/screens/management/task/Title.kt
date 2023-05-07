package com.example.taskmanagementapp.ui.screens.management.task

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskmanagementapp.R
import com.example.taskmanagementapp.ui.theme.Neutral1
import com.example.taskmanagementapp.ui.theme.Neutral8
import com.example.taskmanagementapp.ui.theme.SystemColor
import com.example.taskmanagementapp.ui.theme.VisbyTypography


@Composable
fun Title(
    systemColor: Color = SystemColor
): Pair<String, String> {
    var title by remember { mutableStateOf("Title") }
    var detail by remember { mutableStateOf("Add detail") }

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
                onValueChange = {
                    title = if (it == "") "Title"
                    else it
                },
                modifier = Modifier
                    .fillMaxWidth(),
                textStyle = VisbyTypography.subtitle1,
                maxLines = 1,
            )
            BasicTextField(
                value = detail,
                onValueChange = {
                    detail = it
                },
                modifier = Modifier.fillMaxWidth(),
                textStyle = VisbyTypography.overline,
                maxLines = 3,
            )
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(3.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_add),
                contentDescription = "Add",
                tint = Neutral8,
                modifier = Modifier
                    .size(20.dp)
                    .clip(CircleShape)
                    .background(color = systemColor)
            )
            Text(
                text = "Sticker",
                style = VisbyTypography.overline,
                color = Neutral1
            )
        }
    }
    return Pair(title, detail)
}

@Preview
@Composable
fun TitlePreview() {
    Title()
}