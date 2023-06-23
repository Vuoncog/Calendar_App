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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskmanagementapp.constant.ProfileAreaSettingName
import com.example.taskmanagementapp.constant.ProfileSettingItem
import com.example.taskmanagementapp.constant.loadImage
import com.example.taskmanagementapp.data.SharedViewModel
import com.example.taskmanagementapp.ui.theme.*

@Composable
fun ProfileContentUI(
    onExpandIconClicked: (ProfileSettingItem) -> Unit,
    systemColor: Color = SystemColor,
    subSystemColor: Color = BackgroundColorTask,
    isNotificate: Boolean = false,
    sharedViewModel: SharedViewModel
) {
    val listSettingItems = listOf(
        ProfileSettingItem.NotificationAndAlerts,
        ProfileSettingItem.Color,
        ProfileSettingItem.Settings,
//        ProfileSettingItem.MoreInformation,
        ProfileSettingItem.Logout,
    )

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(vertical = 8.dp)
    ) {
        AvatarInfo(sharedViewModel = sharedViewModel)
        ProfileAreaSetting(
            listSettingItems = listSettingItems,
            title = ProfileAreaSettingName.GENERAL,
            onExpandIconClicked = onExpandIconClicked,
            systemColor = systemColor,
            subSystemColor = subSystemColor,
            isNotificate = isNotificate,
            sharedViewModel = sharedViewModel
        )
        ProfileAreaSetting(
            listSettingItems = listSettingItems,
            title = ProfileAreaSettingName.ACCOUNT,
            onExpandIconClicked = onExpandIconClicked,
            systemColor = systemColor,
            subSystemColor = subSystemColor,
            sharedViewModel = sharedViewModel
        )
    }
}

@Composable
fun AvatarInfo(sharedViewModel: SharedViewModel) {
    val currentUser = sharedViewModel.getCurrentUser()
    val imageUser = loadImage(url = currentUser?.photoUrl.toString(), activity = sharedViewModel.mainActivity).value
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
            imageUser?.let {Image(
                modifier = Modifier
                    .fillMaxSize()
                    .scale(1f, 1f),
                bitmap = imageUser.asImageBitmap(),
                contentDescription = "Avatar",
                contentScale = ContentScale.Crop
            ) }
        }

        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
                text = currentUser?.displayName ?: "No Display Name",
                style = VisbyTypography.h6,
                color = Neutral1
            )

            Text(
                text = currentUser?.email ?: "No Email",
                style = VisbyTypography.body1,
                color = Neutral6
            )
        }
    }
}

@Preview
@Composable
fun ProfilePreview() {

}