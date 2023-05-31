package com.example.taskmanagementapp.constant

import androidx.compose.ui.graphics.Color
import com.example.taskmanagementapp.ui.theme.*

sealed class RecommendColor(
    val primary: Color,
    val secondary: Color
) {
    object Orange : RecommendColor(
        primary = Primary4,
        secondary = BackgroundColorTask
    )
    object Green : RecommendColor(
        primary = GreenPrimary,
        secondary = GreenSecondary
    )
    object Purple : RecommendColor(
        primary = PurplePrimary,
        secondary = PurpleSecondary
    )
}