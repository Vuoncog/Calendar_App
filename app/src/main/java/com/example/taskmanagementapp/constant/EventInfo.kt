package com.example.taskmanagementapp.constant

import androidx.annotation.DrawableRes


 data class EventInfo(val color: Long = 0xFFFAA36A, val title: String, val startTime: Long, val endTime: Long, val detail: String? = null, @DrawableRes val sticker: Int? = null)
 {
    constructor() : this(0xFFFAA36A,"",0L,0L)
 }