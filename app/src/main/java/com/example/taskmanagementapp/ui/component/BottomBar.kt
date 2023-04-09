package com.example.taskmanagementapp.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.taskmanagementapp.constant.BottomBarItems
import com.example.taskmanagementapp.ui.theme.*
import java.util.*

@Composable
fun BottomBar(
    navController: NavHostController,
    resetDay: (Date) -> Unit
) {
    val screens = listOf(
        BottomBarItems.Home,
        BottomBarItems.Calendar,
        BottomBarItems.Management,
        BottomBarItems.Profile,

        )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation(
        contentColor = contentColorFor(backgroundColor = SystemColor),
        backgroundColor = Neutral8,
        elevation = 4.dp,
        modifier = Modifier.border(
            border = BorderStroke(1.dp, NeutralBorder)
        )
    ) {
        screens.forEach { screen ->
            BottomItem(
                screen = screen,
                navController = navController,
                currentDestination = currentDestination,
                resetDay = resetDay
            )
        }
    }
}

@Composable
fun RowScope.BottomItem(
    screen: BottomBarItems,
    navController: NavHostController,
    currentDestination: NavDestination?,
    resetDay: (Date) -> Unit
) {
    val isSelected = currentDestination?.hierarchy?.any {
        it.route == screen.route
    } == true

    BottomNavigationItem(
        selected = isSelected,
        onClick = {
            if (!isSelected) {
                val resetCalendar = Calendar.getInstance()
                resetDay(resetCalendar.time)
                navController.navigate(route = screen.route) {
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                }
            }
        },
        label = {
            Text(
                text = screen.title,
                style = VisbyTypography.caption
            )
        },
        icon = {
            Icon(
                imageVector = ImageVector.vectorResource(
                    id = if (isSelected) screen.iconSelected else screen.icon
                ),
                contentDescription = "Bottom Item Icon",
                tint = if (isSelected) SystemColor else Neutral1
            )

        },
        alwaysShowLabel = false,
    )
}