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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskmanagementapp.constant.ProfileSettingItem
import com.example.taskmanagementapp.constant.SystemColorSet
import com.example.taskmanagementapp.ui.theme.*

@Composable
fun LogoutBottomSheet(
    systemColorSet: SystemColorSet,
    onLogoutClicked: () -> Unit,
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
            profileSettingItem = ProfileSettingItem.Logout,
            onCloseClicked = onCloseClicked,
            systemColorSet = systemColorSet
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Are you sure?".uppercase(),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = VisbyFontFamily,
                letterSpacing = 0.28.sp,
                color = Neutral5
            )
            DangerButton(
                systemColor = systemColorSet.primaryColor,
                onClicked = onLogoutClicked
            )
        }
    }
}

@Composable
fun DangerButton(
    onClicked: () -> Unit,
    systemColor: Color = SystemColor
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(
                color = systemColor
            )
            .padding(
                vertical = 8.dp,
                horizontal = 12.dp
            )
            .clickable {
                onClicked()
            }
    ) {
        Text(
            text = "Log out".uppercase(),
            style = VisbyTypography.subtitle1,
            color = Neutral8
        )
    }
}