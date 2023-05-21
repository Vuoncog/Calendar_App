package com.example.taskmanagementapp.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.taskmanagementapp.R

val VisbyFontFamily: FontFamily = FontFamily(
    Font(R.font.visby_round_cf_bold, FontWeight.Bold),
    Font(R.font.visby_round_cf_demi_bold, FontWeight.SemiBold),
    Font(R.font.visby_round_cf_medium, FontWeight.Medium),
    Font(R.font.visby_round_cf_regular, FontWeight.Normal),
    Font(R.font.visby_round_cf_light, FontWeight.Light),
    Font(R.font.visby_round_cf_extra_light, FontWeight.ExtraLight),
)

val VisbyTypography = Typography(
    h1 = TextStyle(
        fontSize = 98.sp,
        fontFamily = VisbyFontFamily,
        fontWeight = FontWeight.Light,
        letterSpacing = (-1.5).sp
    ),
    h2 = TextStyle(
        fontSize = 60.sp,
        fontFamily = VisbyFontFamily,
        fontWeight = FontWeight.Light,
        letterSpacing = (-0.5).sp
    ),
    h3 = TextStyle(
        fontSize = 48.sp,
        fontFamily = VisbyFontFamily,
        fontWeight = FontWeight.Normal,
        letterSpacing = (0).sp
    ),
    h4 = TextStyle(
        fontSize = 36.sp,
        fontFamily = VisbyFontFamily,
        fontWeight = FontWeight.Normal,
        letterSpacing = (0.25).sp
    ),
    h5 = TextStyle(
        fontSize = 24.sp,
        fontFamily = VisbyFontFamily,
        fontWeight = FontWeight.SemiBold,
        letterSpacing = (0).sp
    ),
    h6 = TextStyle(
        fontSize = 20.sp,
        fontFamily = VisbyFontFamily,
        fontWeight = FontWeight.Medium,
        letterSpacing = (0.15).sp
    ),
    subtitle1 = TextStyle(
        fontSize = 16.sp,
        fontFamily = VisbyFontFamily,
        fontWeight = FontWeight.Medium,
        letterSpacing = (0.15).sp
    ),
    subtitle2 = TextStyle(
        fontSize = 14.sp,
        fontFamily = VisbyFontFamily,
        fontWeight = FontWeight.Medium,
        letterSpacing = (0.56).sp
    ),
    body1 = TextStyle(
        fontSize = 16.sp,
        fontFamily = VisbyFontFamily,
        fontWeight = FontWeight.Normal,
        letterSpacing = (0.5).sp
    ),
    body2 = TextStyle(
        fontSize = 14.sp,
        fontFamily = VisbyFontFamily,
        fontWeight = FontWeight.Normal,
        letterSpacing = (0.35).sp
    ),
    button = TextStyle(
        fontSize = 14.sp,
        fontFamily = VisbyFontFamily,
        fontWeight = FontWeight.SemiBold,
        letterSpacing = (0.28).sp,
    ),
    caption = TextStyle(
        fontSize = 12.sp,
        fontFamily = VisbyFontFamily,
        fontWeight = FontWeight.SemiBold,
        letterSpacing = (0.2).sp
    ),
    overline = TextStyle(
        fontSize = 10.sp,
        fontFamily = VisbyFontFamily,
        fontWeight = FontWeight.Normal,
        letterSpacing = (1.5).sp
    ),
)
