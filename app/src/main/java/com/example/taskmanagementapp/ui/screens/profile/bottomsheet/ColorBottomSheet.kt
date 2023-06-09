package com.example.taskmanagementapp.ui.screens.profile.bottomsheet

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskmanagementapp.R
import com.example.taskmanagementapp.constant.ProfileSettingItem
import com.example.taskmanagementapp.constant.SystemColorSet
import com.example.taskmanagementapp.constant.TaskType
import com.example.taskmanagementapp.constant.ToDoTask
import com.example.taskmanagementapp.ui.screens.home.HomeTodoTaskItem
import com.example.taskmanagementapp.ui.screens.home.MiniDetail
import com.example.taskmanagementapp.ui.theme.Neutral2
import com.example.taskmanagementapp.ui.theme.VisbyFontFamily
import com.example.taskmanagementapp.ui.theme.VisbyTypography


@Composable
fun ColorBottomSheet(
    systemColorSet: SystemColorSet,
    onColorChange: (SystemColorSet) -> Unit,
    onCheckClicked: (SystemColorSet) -> Unit,
    onCloseClicked: () -> Unit
) {
    val recommendColorSet = listOf(
        SystemColorSet.ORANGE,
        SystemColorSet.GREEN,
        SystemColorSet.PURPLE,
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
            systemColorSet = systemColorSet,
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
            HomeCardPreview(
                sticker = systemColorSet.listStickerSet[17],
                systemColor = systemColorSet.primaryColor,
                subSystemColor = systemColorSet.secondaryColor
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
                recommendColorSet.forEach { displayColor ->
                    ColorCircle(
                        onColorCircleClicked = {
                            /* TODO */
                        },
                        color = displayColor,
                        systemColorSet = systemColorSet,
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
    onColorCircleClicked: (SystemColorSet) -> Unit,
    color: SystemColorSet,
    systemColorSet: SystemColorSet,
    onColorChange: (SystemColorSet) -> Unit,
    circleSize: Dp = 20.dp
) {
    val isSelected = color == systemColorSet

    val borderModifier = Modifier.border(
        width = 1.dp,
        shape = CircleShape,
        color = color.primaryColor
    )

    val noBorder = Modifier.border(
        width = 0.dp,
        shape = CircleShape,
        color = color.primaryColor
    )
    Canvas(
        modifier = Modifier
            .size(circleSize)
            .clip(CircleShape)
            .then(if (isSelected) borderModifier else noBorder)
            .clickable {
                onColorChange(color)
                Log.d("Circle selected: ", isSelected.toString())
                onColorCircleClicked(color)
            },
        onDraw = {
            drawCircle(
                color = color.primaryColor,
                radius = if (isSelected) ((circleSize).value * 3 / 5) else size.minDimension / 2.0f
            )
        }
    )
}

@Composable
fun HomeCardPreview(
    @DrawableRes sticker: Int,
    systemColor: Color,
    subSystemColor: Color,
) {
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
                text = "This is event card",
                style = VisbyTypography.h6,
                maxLines = 2,
                color = Neutral2
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                MiniDetail(
                    icon = R.drawable.ic_clock,
                    title = "Time",
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
fun ProfileColorPreview() {
    ColorBottomSheet(
        SystemColorSet.ORANGE,
        {},
        {}, {})
}