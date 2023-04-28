package com.example.taskmanagementapp.ui.screens.profile.bottomsheet

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskmanagementapp.R
import com.example.taskmanagementapp.constant.ProfileSettingItem
import com.example.taskmanagementapp.constant.RecommendColor
import com.example.taskmanagementapp.constant.TaskType
import com.example.taskmanagementapp.ui.screens.home.HomeTodoTaskItem
import com.example.taskmanagementapp.ui.theme.Primary4
import com.example.taskmanagementapp.ui.theme.VisbyFontFamily


@Composable
fun ColorBottomSheet(
    systemColor: Color,
    onColorChange: (Color) -> Unit,
    onCheckClicked: (Color) -> Unit,
    onCloseClicked: () -> Unit
) {
    val recommendColor = listOf(
        RecommendColor.Orange,
        RecommendColor.Green,
        RecommendColor.Purple,
    )

    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(
                horizontal = 16.dp,
                vertical = 12.dp
            )
            .padding(bottom = 56.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        TitleBottomSheet(
            profileSettingItem = ProfileSettingItem.Color,
            systemColor = systemColor,
            onCheckClicked = onCheckClicked,
            onCloseClicked = onCloseClicked
        )

        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
                text = "Preview".uppercase(),
                fontFamily = VisbyFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                letterSpacing = 0.28.sp,
            )
            HomeTodoTaskItem(
                taskType = TaskType.Shopping,
                systemColor = systemColor
            )
        }

        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
                text = "Color adjustment".uppercase(),
                fontFamily = VisbyFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                letterSpacing = 0.28.sp,
            )
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(color = Color(0xFFF8F8F8))
                    .background(color = Color(0xFFF8F8F8))
                    .padding(
                        vertical = 8.dp,
                        horizontal = 16.dp
                    )
            ) {
                recommendColor.forEach { color ->
                    ColorCircle(
                        onColorCircleClicked = {
                            /* TODO */
                        },
                        color = color,
                        systemColor = systemColor,
                        onColorChange = onColorChange
                    )
                }

                ColorPicker()
            }
        }
    }
}

@Composable
fun ColorPicker() {
    Box(
        modifier = Modifier
            .size(20.dp)
            .clip(CircleShape)
            .border(
                width = 2.dp,
                shape = CircleShape,
                color = Color.Transparent
            )
            .graphicsLayer {
                clip = true
                shape = CircleShape

            }
    ) {
        Image(
            painter = painterResource(id = R.drawable.colorwheel),
            contentDescription = "Color Picker",
            modifier = Modifier.size(20.dp)
        )
    }
}

@Composable
fun ColorCircle(
    onColorCircleClicked: (Color) -> Unit,
    color: Color,
    systemColor: Color,
    onColorChange: (Color) -> Unit
) {
    val isSelected = color == systemColor

    val borderModifier = Modifier.border(
        width = 1.dp,
        shape = CircleShape,
        color = color
    )

    val noBorder = Modifier.border(
        width = 0.dp,
        shape = CircleShape,
        color = color
    )
    Canvas(
        modifier = Modifier
            .size(20.dp)
            .clip(CircleShape)
            .then(if (isSelected) borderModifier else noBorder)
            .clickable {
                onColorChange(color)
                Log.d("Circle selected: ", isSelected.toString())
                onColorCircleClicked(color)
            },
        onDraw = {
            drawCircle(
                color = color,
                radius = if (isSelected) 12.dp.value else size.minDimension / 2.0f
            )
        }
    )
}

@Preview
@Composable
fun ProfileColorPreview() {
    ColorBottomSheet(
        Primary4,
        {},
        {}, {})
}