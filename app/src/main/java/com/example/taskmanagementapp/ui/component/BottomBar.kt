package com.example.taskmanagementapp.ui.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.DialogNavigator
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.taskmanagementapp.constant.BottomBarItems

@Composable
fun BottomBar(
    navController: NavHostController
) {
    val screens = listOf(
        BottomBarItems.Home,
        BottomBarItems.Profile,
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation {
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
    BottomNavigationItem(
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        onClick = {
            navController.navigate(route = screen.route)
        },
        icon = {
            Icon(
                imageVector = ImageVector.vectorResource(screen.icon),
                contentDescription = "Bottom Item Icon"
            )
        }
    )
}