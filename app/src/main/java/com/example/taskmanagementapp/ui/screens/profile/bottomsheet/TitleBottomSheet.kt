package com.example.taskmanagementapp.ui.screens.profile.bottomsheet

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskmanagementapp.R
import com.example.taskmanagementapp.constant.ProfileSettingItem
import com.example.taskmanagementapp.constant.SystemColorSet
import com.example.taskmanagementapp.ui.theme.Neutral1
import com.example.taskmanagementapp.ui.theme.SystemColor
import com.example.taskmanagementapp.ui.theme.VisbyTypography

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TitleBottomSheet(
    profileSettingItem: ProfileSettingItem,
    systemColorSet: SystemColorSet,
    onCheckClicked: ((SystemColorSet) -> Unit)? = null,
    onCloseClicked: () -> Unit
) {
    CompositionLocalProvider(LocalMinimumTouchTargetEnforcement provides false) {
        Row(
            verticalAlignment = CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp)
        ) {
            CloseIcon(
                systemColor = systemColorSet.primaryColor,
                onCloseClicked = onCloseClicked
            )
            Text(
                text = profileSettingItem.title,
                style = VisbyTypography.subtitle1,
                modifier = Modifier
                    .weight(1f)
                    .align(CenterVertically)
                    .padding(end = if (onCheckClicked == null) 32.dp else 0.dp),
                textAlign = TextAlign.Center,
                color = Neutral1,
                fontWeight = FontWeight.SemiBold
            )
            if (onCheckClicked != null) {
                CheckIcon(
                    systemColorSet = systemColorSet,
                    onCheckClicked = onCheckClicked
                )
            }
        }
    }
}

@Composable
fun CloseIcon(
    systemColor: Color = SystemColor,
    onCloseClicked: () -> Unit,
) {
    IconButton(onClick = {
        onCloseClicked()
    }) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_remove),
            contentDescription = "Close icon",
            tint = systemColor,
            modifier = Modifier.size(32.dp)
        )
    }
}

@Composable
fun CheckIcon(
    systemColorSet: SystemColorSet,
    onCheckClicked: ((SystemColorSet) -> Unit)? = null,
) {
    if (onCheckClicked != null) {
        IconButton(onClick = {
            onCheckClicked(systemColorSet)
        }) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_tick),
                contentDescription = "Close icon",
                tint = systemColorSet.primaryColor,
                modifier = Modifier.size(32.dp)
            )
        }
    }
}

fun <T> onCheckClicked(type: T): (T) -> T = { it }

@Preview
@Composable
fun TitleBottomSheetPreview() {
    TitleBottomSheet(
        ProfileSettingItem.Color,
        onCheckClicked = null,
        onCloseClicked = {},
        systemColorSet = SystemColorSet.ORANGE
    )
}

@Preview
@Composable
fun TitleBottomSheetPreview2() {
    TitleBottomSheet(
        ProfileSettingItem.Color,
        onCheckClicked = {},
        onCloseClicked = {},
        systemColorSet = SystemColorSet.ORANGE
    )
}