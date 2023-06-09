package com.example.taskmanagementapp.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.taskmanagementapp.constant.GraphRoute
import com.example.taskmanagementapp.ui.theme.Neutral1
import com.example.taskmanagementapp.ui.theme.NeutralBorder
import com.example.taskmanagementapp.ui.theme.VisbyTypography
import java.util.*

@Composable
fun BottomBar(
    navController: NavHostController,
    resetDay: (Date) -> Unit,
    systemColor: Color,
) {
    val screens = listOf(
        GraphRoute.Home,
        GraphRoute.Calendar,
        GraphRoute.Management,
        GraphRoute.Profile,
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation(
        contentColor = contentColorFor(backgroundColor = systemColor),
        backgroundColor = Color.White,
        elevation = 4.dp,
        modifier = Modifier.border(
            border = BorderStroke(1.dp, NeutralBorder)
        )
    ) {
        screens.forEach { screen ->
            BottomItem(
                screen = screen,
                navController = navController,
                systemColor = systemColor,
                currentDestination = currentDestination,
                resetDay = resetDay,
            )
        }
    }
}

@Composable
fun RowScope.BottomItem(
    screen: GraphRoute,
    navController: NavHostController,
    systemColor: Color,
    currentDestination: NavDestination?,
    resetDay: (Date) -> Unit,
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
                tint = if (isSelected) systemColor else Neutral1
            )

        },
        alwaysShowLabel = false,
    )
}