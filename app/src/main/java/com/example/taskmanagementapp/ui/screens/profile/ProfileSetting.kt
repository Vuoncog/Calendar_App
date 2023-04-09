package com.example.taskmanagementapp.ui.screens.profile

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.taskmanagementapp.R
import com.example.taskmanagementapp.constant.ProfileSettingItem
import com.example.taskmanagementapp.constant.StateSettingItem
import com.example.taskmanagementapp.ui.theme.*

@Composable
fun ProfileAreaSetting(
    listSettingItems: List<ProfileSettingItem>,
    onExpandIconClicked: (ProfileSettingItem) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(color = BackgroundColorTask)
            .padding(
                horizontal = 8.dp,
                vertical = 12.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        items(listSettingItems){
                item: ProfileSettingItem ->
            SettingItem(
                profileSettingItem = item,
                onExpandIconClicked = onExpandIconClicked
            )
        }
    }
}

@Composable
fun SettingItem(
    profileSettingItem: ProfileSettingItem,
    onExpandIconClicked: (ProfileSettingItem) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ){
        Icon(
            imageVector = ImageVector.vectorResource(profileSettingItem.icon),
            contentDescription = "Setting icon",
            tint = SystemColor
        )

        Text(text = profileSettingItem.title,
        style = VisbyTypography.body2,
        color = Neutral2,
        modifier = Modifier.weight(1f))

        StateIcon(state = profileSettingItem.stateSettingItem)

        ExpandIcon(
            onExpandIconClicked = onExpandIconClicked,
            profileSettingItem = profileSettingItem
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
            .size(24.dp)
            .clickable {
                onExpandIconClicked(profileSettingItem)
            }
    )
}

@Composable
fun StateIcon(
    state: StateSettingItem
) {
    when(state){
        StateSettingItem.SWITCH -> {
            Text(text = "On",
            style = VisbyTypography.body2,
            color = Neutral2)
        }
        
        StateSettingItem.COLOR -> {
            Canvas(
                modifier = Modifier.size(16.dp),
                onDraw = {
                    drawCircle(color = SystemColor)
                }
            )
        }

        StateSettingItem.NONE -> {

        }
    }
}