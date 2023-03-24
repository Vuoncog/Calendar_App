package com.example.taskmanagementapp.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.taskmanagementapp.R

val visbyFontFamily: FontFamily = FontFamily(
    Font(R.font.visby_round_cf_bold, FontWeight.Bold),
    Font(R.font.visby_round_cf_demi_bold, FontWeight.SemiBold),
    Font(R.font.visby_round_cf_medium, FontWeight.Medium),
    Font(R.font.visby_round_cf_regular, FontWeight.Normal),
    Font(R.font.visby_round_cf_light, FontWeight.Light),
    Font(R.font.visby_round_cf_extra_light, FontWeight.ExtraLight),
)

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)