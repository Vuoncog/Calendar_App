package com.example.taskmanagementapp.ui.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskmanagementapp.R
import com.example.taskmanagementapp.constant.ProfileAreaSettingName
import com.example.taskmanagementapp.constant.ProfileSettingItem
import com.example.taskmanagementapp.ui.theme.Neutral1
import com.example.taskmanagementapp.ui.theme.Neutral4
import com.example.taskmanagementapp.ui.theme.Neutral6
import com.example.taskmanagementapp.ui.theme.VisbyTypography

@Composable
fun ProfileContent(
    onExpandIconClicked: (ProfileSettingItem) -> Unit
) {
    val listGeneralSettingItems = listOf(
        ProfileSettingItem.NotificationAndAlerts,
        ProfileSettingItem.Color,
        ProfileSettingItem.Settings,
    )
    val listAccountSettingItems = listOf(
        ProfileSettingItem.MoreInformation,
        ProfileSettingItem.Logout,
    )

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        AvatarInfo()
        ProfileSettings(
            listSettingItems = listGeneralSettingItems,
            title = ProfileAreaSettingName.GENERAL,
            onExpandIconClicked = onExpandIconClicked
        )
        ProfileSettings(
            listSettingItems = listAccountSettingItems,
            title = ProfileAreaSettingName.ACCOUNT,
            onExpandIconClicked = onExpandIconClicked
        )
    }
}

@Composable
fun ProfileSettings(
    listSettingItems: List<ProfileSettingItem>,
    title: ProfileAreaSettingName,
    onExpandIconClicked: (ProfileSettingItem) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = title.areaName,
            style = VisbyTypography.subtitle1,
            color = Neutral4,
            modifier = Modifier.padding(start = 16.dp)
        )

        when (title) {
            ProfileAreaSettingName.GENERAL -> {
                ProfileAreaSetting(
                    listSettingItems = listSettingItems,
                    onExpandIconClicked = onExpandIconClicked
                )
            }
            ProfileAreaSettingName.ACCOUNT -> {
                ProfileAreaSetting(
                    listSettingItems = listSettingItems,
                    onExpandIconClicked = onExpandIconClicked
                )
            }
        }
    }
}

@Composable
fun AvatarInfo() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        modifier = Modifier.padding(
            start = 16.dp,
            end = 16.dp,
            bottom = 8.dp
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape),
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .scale(1f,1f),
                painter = painterResource(id = R.drawable.avatar),
                contentDescription = "Avatar",
                contentScale = ContentScale.Crop
            )
        }

        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
                text = "Van Vuong",
                style = VisbyTypography.h6,
                color = Neutral1
            )

            Text(
                text = "vuon.co.g@gmail.com",
                style = VisbyTypography.body1,
                color = Neutral6
            )
        }
    }
}

@Preview
@Composable
fun ProfilePreview() {
    ProfileContent({})
}