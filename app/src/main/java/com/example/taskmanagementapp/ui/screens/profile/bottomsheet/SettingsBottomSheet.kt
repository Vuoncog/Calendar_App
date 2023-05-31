package com.example.taskmanagementapp.ui.screens.profile.bottomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskmanagementapp.constant.ProfileSettingItem
import com.example.taskmanagementapp.constant.SystemColorSet
import com.example.taskmanagementapp.ui.theme.*

@Composable
fun SettingsBottomSheet(
    systemColorSet: SystemColorSet,
    onBackupClicked: () -> Unit,
    onCloseClicked: () -> Unit
) {
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
            systemColorSet = systemColorSet,
            profileSettingItem = ProfileSettingItem.Settings,
            onCheckClicked = null,
            onCloseClicked = onCloseClicked
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Store your data".uppercase(),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = VisbyFontFamily,
                letterSpacing = 0.28.sp,
                color = Neutral5
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                ProfileButton(
                    systemColor = systemColorSet.primaryColor,
                    onBackupClicked = onBackupClicked
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Backup that save data and recover in other devices",
                    style = VisbyTypography.body2,
                    color = Neutral5,
                    maxLines = 2,
                )

            }

        }
    }
}

@Composable
fun ProfileButton(
    systemColor: Color = SystemColor,
    onBackupClicked: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(
                color = BackgroundColorTask
            )
            .padding(
                vertical = 8.dp,
                horizontal = 12.dp
            )
            .clickable {
                onBackupClicked()
            }
    ) {
        Text(
            text = "Backup Now",
            style = VisbyTypography.subtitle1,
            color = systemColor
        )
    }
}

@Preview
@Composable
fun SettingsPreview() {
    SettingsBottomSheet(
        onBackupClicked = {},
        onCloseClicked = {},
        systemColorSet = SystemColorSet.ORANGE
    )
}