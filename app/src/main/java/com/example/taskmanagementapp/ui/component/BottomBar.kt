package com.example.taskmanagementapp.ui.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.DialogNavigator
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.taskmanagementapp.constant.BottomBarItems
import com.example.taskmanagementapp.ui.theme.*

@Composable
fun BottomBar(
    navController: NavHostController
) {
    val screens = listOf(
        BottomBarItems.Home,
        BottomBarItems.Calendar,
        BottomBarItems.Memo,
        BottomBarItems.Profile,

        )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation(
        contentColor = contentColorFor(backgroundColor = Primary5),
        backgroundColor = Neutral8,
        elevation = 4.dp,
    ) {
        screens.forEach { screen ->
            BottomItem(
                screen = screen,
                navController = navController,
                currentDestination = currentDestination
            )
        }
    }
}

@Composable
fun RowScope.BottomItem(
    screen: BottomBarItems,
    navController: NavHostController,
    currentDestination: NavDestination?
) {
    val isSelected = currentDestination?.hierarchy?.any {
        it.route == screen.route
    } == true

    BottomNavigationItem(
        selected = isSelected,
        onClick = {
            navController.navigate(route = screen.route)
        },
        label = {
            Text(
                text = screen.title,
                style = VisbyTypography.caption
            )
        },
        icon = {
            if (isSelected) {
                Icon(
                    imageVector = ImageVector.vectorResource(screen.iconSelected),
                    contentDescription = "Bottom Item Icon Selected",
                    tint = Primary4
                )
            } else {
                Icon(
                    imageVector = ImageVector.vectorResource(screen.icon),
                    contentDescription = "Bottom Item Icon",
                    tint = Neutral1
                )
            }
        }, alwaysShowLabel = false
    )
}