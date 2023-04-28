package com.example.taskmanagementapp.constant

import androidx.compose.ui.graphics.Color

data class EventInfo(
    val color: Color,
    val eventName: String,
    val startTime: Float,
    val timeRange: Float,
    val subscription: String? = null
)