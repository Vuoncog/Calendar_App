package com.example.taskmanagementapp.ui.theme

import androidx.annotation.ColorInt
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.graphics.ColorUtils

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

val Primary1 = Color(0xFFFEE6DF)
val Primary2 = Color(0xFFFEC7A6)
val Primary3 = Color(0xFFFAA36A)
val Primary4 = Color(0xFF964908)
//val Primary4 = Color(0xFF6B3811)
val BackgroundColorTask = Color(0xFFF8F2F3)
val Neutral1 = Color(0xFF1F1A17)
val Neutral2 = Color(0xFF352F2B)
val Neutral3 = Color(0xFF4D4441)
val Neutral4 = Color(0xFF645E59)
val Neutral5 = Color(0xFF7D7571)
val Neutral6 = Color(0xFF97908B)
val Neutral7 = Color(0xFFB2A9A5)
val Neutral8 = Color(0xFFFEFBFF)
val NeutralBorder = Color(0xFFEAEAEA)

var SystemColor = Primary4

fun ChangeLightness(
    color: Color
){
    @ColorInt val intColor = color.toArgb()
    val hsl = FloatArray(3)
    val runHsl = ColorUtils.colorToHSL(intColor, hsl)
    hsl[0] = 0.95f

    SystemColor = Color(ColorUtils.HSLToColor(hsl))
}

