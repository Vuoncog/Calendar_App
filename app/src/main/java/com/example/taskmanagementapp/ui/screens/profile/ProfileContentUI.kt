package com.example.taskmanagementapp.ui.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskmanagementapp.R
import com.example.taskmanagementapp.ViewModel.LogInViewModel
import com.example.taskmanagementapp.constant.ProfileAreaSettingName
import com.example.taskmanagementapp.constant.ProfileSettingItem
import com.example.taskmanagementapp.ui.theme.Neutral1
import com.example.taskmanagementapp.ui.theme.Neutral6
import com.example.taskmanagementapp.ui.theme.SystemColor
import com.example.taskmanagementapp.ui.theme.VisbyTypography

@Composable
fun ProfileContentUI(
    onExpandIconClicked: (ProfileSettingItem) -> Unit,
    systemColor: Color = SystemColor,
    isNotificate: Boolean = false,
    logInViewModel: LogInViewModel?
) {
    val listSettingItems = listOf(
        ProfileSettingItem.NotificationAndAlerts,
        ProfileSettingItem.Color,
        ProfileSettingItem.Settings,
        ProfileSettingItem.MoreInformation,
        ProfileSettingItem.Logout,
    )

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(vertical = 8.dp)
    ) {
        AvatarInfo(logInViewModel = logInViewModel)
        ProfileAreaSetting(
            listSettingItems = listSettingItems,
            title = ProfileAreaSettingName.GENERAL,
            onExpandIconClicked = onExpandIconClicked,
            systemColor = systemColor,
            isNotificate = isNotificate
            systemColor = systemColor,
            logInViewModel = logInViewModel
        )
        ProfileAreaSetting(
            listSettingItems = listSettingItems,
            title = ProfileAreaSettingName.ACCOUNT,
            onExpandIconClicked = onExpandIconClicked,
            systemColor = systemColor,
            logInViewModel = logInViewModel
        )
    }
}

@Composable
fun AvatarInfo(logInViewModel: LogInViewModel?) {
    val currentUser = logInViewModel?.getCurrentUser()
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
                text = if(currentUser != null){currentUser.displayName!!}else{""},
                style = VisbyTypography.h6,
                color = Neutral1
            )

            Text(
                text = if(currentUser != null){currentUser.email!!}else{""},
                style = VisbyTypography.body1,
                color = Neutral6
            )
        }
    }
}

@Preview
@Composable
fun ProfilePreview() {
    ProfileContentUI({}, logInViewModel = null)
}