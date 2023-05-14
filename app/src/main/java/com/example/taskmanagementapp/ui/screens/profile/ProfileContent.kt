package com.example.taskmanagementapp.ui.screens.profile

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.taskmanagementapp.constant.BottomBarItems
import com.example.taskmanagementapp.constant.ProfileSettingItem
import com.example.taskmanagementapp.data.SharedViewModel
import com.example.taskmanagementapp.ui.screens.profile.bottomsheet.ColorBottomSheet
import com.example.taskmanagementapp.ui.screens.profile.bottomsheet.LogoutBottomSheet
import com.example.taskmanagementapp.ui.screens.profile.bottomsheet.NotificationBottomSheet
import com.example.taskmanagementapp.ui.screens.profile.bottomsheet.SettingsBottomSheet
import com.example.taskmanagementapp.ui.theme.Primary4
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.bottomSheet
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import kotlinx.coroutines.launch
import java.util.*

@OptIn(ExperimentalMaterialNavigationApi::class)
@Composable
fun ProfileContent(
    sharedViewModel: SharedViewModel
) {
    val bottomSheetNavigator = rememberBottomSheetNavigator()
    val navController = rememberNavController(bottomSheetNavigator)
    val coroutineScope = rememberCoroutineScope()

    val openBottomSheet: (ProfileSettingItem) -> Unit = { profileSettingItem ->
        coroutineScope.launch {
            navController.navigate("profile/${profileSettingItem.route}")
        }
    }

    val closeBottomSheet: () -> Unit = {
        coroutineScope.launch {
            navController.popBackStack()
        }
    }

    var isNotificate by remember { mutableStateOf(false) }
    var systemColor by remember { mutableStateOf(Primary4) }
    var systemColorPreview by remember { mutableStateOf(systemColor) }

    ModalBottomSheetLayout(
        bottomSheetNavigator = bottomSheetNavigator,
        sheetShape = RoundedCornerShape(
            topStart = 24.dp,
            topEnd = 24.dp
        ),
        sheetElevation = 4.dp
    ) {
        NavHost(
            navController = navController,
            startDestination = BottomBarItems.Profile.route
        ) {
            composable(BottomBarItems.Profile.route) {
                ProfileContentUI(
                    onExpandIconClicked = {
                        openBottomSheet(it)
                    },
                    systemColor = systemColor,

                    isNotificate = isNotificate,
                    sharedViewModel = sharedViewModel
                )
            }

            bottomSheet(route = "${BottomBarItems.Profile.route}/${ProfileSettingItem.NotificationAndAlerts.route}") {
                NotificationBottomSheet(
                    isNotificate = isNotificate,
                    systemColor = systemColor,
                    onSwitchClicked = {
                        isNotificate = it
                    },
                    onCloseClicked = {
                        closeBottomSheet()
                    }
                )
            }

            bottomSheet(route = "${BottomBarItems.Profile.route}/${ProfileSettingItem.Color.route}") {
                ColorBottomSheet(
                    systemColor = systemColorPreview,
                    onColorChange = {
                        systemColorPreview = it
                    },
                    onCheckClicked = {
                        systemColor = it
                        closeBottomSheet()
                    },
                    onCloseClicked = {
                        closeBottomSheet()
                    }
                )
            }

            bottomSheet(route = "${BottomBarItems.Profile.route}/${ProfileSettingItem.Settings.route}") {
                SettingsBottomSheet(
                    systemColor = systemColor,
                    onBackupClicked = {},
                    onCloseClicked = {
                        closeBottomSheet()
                    }
                )
            }

            bottomSheet(route = "${BottomBarItems.Profile.route}/${ProfileSettingItem.Logout.route}") {
                LogoutBottomSheet(
                    systemColor = systemColor,
                    onLogoutClicked = {},
                    onCloseClicked = {
                        closeBottomSheet()
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun ProfileContentPreview() {

}