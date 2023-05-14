package com.example.taskmanagementapp.ui.screens.profile

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskmanagementapp.R
import com.example.taskmanagementapp.constant.ProfileAreaSettingName
import com.example.taskmanagementapp.constant.ProfileSettingItem
import com.example.taskmanagementapp.constant.StateSettingItem
import com.example.taskmanagementapp.data.SharedViewModel
import com.example.taskmanagementapp.ui.theme.*

@Composable
fun ProfileAreaSetting(
    listSettingItems: List<ProfileSettingItem>,
    title: ProfileAreaSettingName,
    onExpandIconClicked: (ProfileSettingItem) -> Unit,
    systemColor: Color = SystemColor,
    isNotificate: Boolean = false,
    sharedViewModel: SharedViewModel
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = title.areaName,
            style = VisbyTypography.subtitle1,
            color = Neutral4,
            modifier = Modifier.padding(
                start = 16.dp
            )
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(color = BackgroundColorTask),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            listSettingItems.forEach { item: ProfileSettingItem ->
                if (item.area.areaName == title.areaName) {
                    if (item.title == "Log Out") {
                        SettingItem(
                            profileSettingItem = item,
                            onExpandIconClicked = {
                                sharedViewModel.navigateToLogin.invoke()
                                sharedViewModel.signOut()
                            },
                            systemColor = systemColor,
                            isNotificate = isNotificate
                        )
                    } else {
                        SettingItem(
                            profileSettingItem = item,
                            onExpandIconClicked = onExpandIconClicked,
                            systemColor = systemColor,
                            isNotificate = isNotificate
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SettingItem(
    profileSettingItem: ProfileSettingItem,
    onExpandIconClicked: (ProfileSettingItem) -> Unit,
    systemColor: Color = SystemColor,
    isNotificate: Boolean = false
) {
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(focusRequester)
            .clickable {
                onExpandIconClicked(profileSettingItem)
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(profileSettingItem.icon),
            contentDescription = "Setting icon",
            tint = systemColor,
            modifier = Modifier.padding(8.dp)
        )

        Text(
            text = profileSettingItem.title,
            style = VisbyTypography.body2,
            color = Neutral2,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.weight(1f)
        )

        StateIcon(
            state = profileSettingItem.stateSettingItem,
            systemColor = systemColor,
            isNotificate = isNotificate,
        )

        ExpandIcon(
            profileSettingItem = profileSettingItem,
            onExpandIconClicked = onExpandIconClicked,
        )
    }
}

@Composable
fun ExpandIcon(
    profileSettingItem: ProfileSettingItem,
    onExpandIconClicked: (ProfileSettingItem) -> Unit
) {
    Icon(
        imageVector = ImageVector.vectorResource(id = R.drawable.ic_chevron_right),
        contentDescription = "Expand Icon",
        tint = Neutral5,
        modifier = Modifier
            .padding(8.dp)
            .size(16.dp)
            .clickable {
                onExpandIconClicked(profileSettingItem)
            }
    )
}

@Composable
fun StateIcon(
    state: StateSettingItem,
    isNotificate: Boolean = false,
    systemColor: Color = SystemColor
) {
    when (state) {
        StateSettingItem.SWITCH -> {
            Text(
                text = if (isNotificate) "On" else "Off",
                style = VisbyTypography.body2,
                color = Neutral2
            )
        }

        StateSettingItem.COLOR -> {
            Canvas(
                modifier = Modifier.size(16.dp),
                onDraw = {
                    drawCircle(color = systemColor)
                }
            )
        }

        StateSettingItem.NONE -> {

        }
    }
}

@Preview
@Composable
fun PreviewAreaProfile() {

}