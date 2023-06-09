package com.example.taskmanagementapp.ui.screens.profile

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.taskmanagementapp.constant.GraphRoute
import com.example.taskmanagementapp.constant.ProfileSettingItem
import com.example.taskmanagementapp.constant.SystemColorSet
import com.example.taskmanagementapp.data.SharedViewModel
import com.example.taskmanagementapp.ui.screens.profile.bottomsheet.ColorBottomSheet
import com.example.taskmanagementapp.ui.screens.profile.bottomsheet.LogoutBottomSheet
import com.example.taskmanagementapp.ui.screens.profile.bottomsheet.NotificationBottomSheet
import com.example.taskmanagementapp.ui.screens.profile.bottomsheet.SettingsBottomSheet
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.bottomSheet
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import kotlinx.coroutines.launch
import java.util.*

@OptIn(ExperimentalMaterialNavigationApi::class)
@Composable
fun ProfileContent(
    sharedViewModel: SharedViewModel,
    systemColorSet: SystemColorSet,
    onColorChange: (SystemColorSet) -> Unit,
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
    var systemColor by remember { mutableStateOf(systemColorSet) }

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
            startDestination = GraphRoute.Profile.route
        ) {
            composable(GraphRoute.Profile.route) {
                ProfileContentUI(
                    onExpandIconClicked = {
                        openBottomSheet(it)
                    },
                    systemColor = systemColorSet.primaryColor,
                    subSystemColor = systemColorSet.secondaryColor,
                    isNotificate = isNotificate,
                    sharedViewModel = sharedViewModel
                )
            }

            bottomSheet(route = "${GraphRoute.Profile.route}/${ProfileSettingItem.NotificationAndAlerts.route}") {
                NotificationBottomSheet(
                    isNotificate = isNotificate,
                    systemColorSet = systemColorSet,
                    onSwitchClicked = {
                        isNotificate = it
                    },
                    onCloseClicked = {
                        closeBottomSheet()
                    }
                )
            }

            bottomSheet(route = "${GraphRoute.Profile.route}/${ProfileSettingItem.Color.route}") {
                ColorBottomSheet(
                    systemColorSet = systemColor,
                    onColorChange = {
                        systemColor= it
                    },
                    onCheckClicked = { chosenColor ->
                        onColorChange(chosenColor)
                        closeBottomSheet()
                    },
                    onCloseClicked = {
                        closeBottomSheet()
                    }
                )
            }

            bottomSheet(route = "${GraphRoute.Profile.route}/${ProfileSettingItem.Settings.route}") {
                SettingsBottomSheet(
                    systemColorSet = systemColorSet,
                    onBackupClicked = {},
                    onCloseClicked = {
                        closeBottomSheet()
                    }
                )
            }

            bottomSheet(route = "${GraphRoute.Profile.route}/${ProfileSettingItem.Logout.route}") {
                LogoutBottomSheet(
                    systemColorSet = systemColorSet,
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