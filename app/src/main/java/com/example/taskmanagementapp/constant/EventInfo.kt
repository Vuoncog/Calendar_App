package com.example.taskmanagementapp.constant

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color

data class EventInfo(
    val color: Color,
    val title: String,
    val startTime: Float,
    val timeRange: Float,
    val detail: String? = null,
    @DrawableRes val sticker: Int? = null
    )