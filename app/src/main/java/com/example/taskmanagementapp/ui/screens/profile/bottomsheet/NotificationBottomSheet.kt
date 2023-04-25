package com.example.taskmanagementapp.ui.screens.profile.bottomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalMinimumTouchTargetEnforcement
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskmanagementapp.constant.ProfileSettingItem
import com.example.taskmanagementapp.ui.theme.Neutral5
import com.example.taskmanagementapp.ui.theme.Neutral8
import com.example.taskmanagementapp.ui.theme.SystemColor
import com.example.taskmanagementapp.ui.theme.VisbyTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationBottomSheet(
    systemColor: Color = SystemColor,
    onSwitchClicked: (Boolean) -> Unit,
    onCloseClicked: () -> Unit,
    isNotificate: Boolean = false
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
            systemColor = systemColor,
            profileSettingItem = ProfileSettingItem.NotificationAndAlerts,
            onCloseClicked = onCloseClicked
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Enable notification",
                style = VisbyTypography.subtitle1,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 12.dp)
            )
            CompositionLocalProvider(LocalMinimumTouchTargetEnforcement provides false) {
                Switch(
                    checked = isNotificate,
                    onCheckedChange = {
                        onSwitchClicked(it)
                    },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Neutral8,
                        checkedTrackColor = systemColor,
                        uncheckedThumbColor = Neutral5,
                        uncheckedTrackColor = Neutral8,
                        uncheckedBorderColor = Neutral5,
                    )
                )
            }
        }
    }
}

@Preview
@Composable
fun NnAPreview() {
    NotificationBottomSheet(
        onSwitchClicked = {  },
        onCloseClicked = {},
    )
}