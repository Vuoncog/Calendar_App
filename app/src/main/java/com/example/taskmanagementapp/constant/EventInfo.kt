package com.example.taskmanagementapp.constant

import androidx.annotation.DrawableRes


 data class EventInfo(
    val color: Long = 0xFFFAA36A,
    val title: String,
    val startTime: Float,
    val endTime: Float,
    val detail: String? = null,
    @DrawableRes val sticker: Int? = null
    )
 {
    constructor() : this(0xFFFAA36A,"",0F,0F)
 }