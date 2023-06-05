package com.example.taskmanagementapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import com.example.taskmanagementapp.constant.SystemColorSet

@Composable
fun M3Theme(
    systemColorSet: SystemColorSet,
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val lightColorScheme = lightColorScheme(
        primary = systemColorSet.primaryColor,
        onPrimary = md_theme_light_onPrimary,
        primaryContainer = md_theme_light_primaryContainer,
        onPrimaryContainer = Purple500,
        secondaryContainer = systemColorSet.secondaryColor,
        secondary = Neutral1
    )

    val darkColorScheme = darkColorScheme(
        primary = md_theme_dark_primary,
        onPrimary = md_theme_dark_onPrimary,
        primaryContainer = md_theme_dark_primaryContainer,
// ..
    )

    val colorScheme =
        if (!useDarkTheme) {
            lightColorScheme
        } else {
            darkColorScheme
        }
    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}