package com.example.taskmanagementapp.ui.component

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskmanagementapp.ui.theme.Neutral7
import com.example.taskmanagementapp.ui.theme.SystemColor
import com.example.taskmanagementapp.ui.theme.VisbyFontFamily
import com.example.taskmanagementapp.ui.theme.VisbyTypography

@Composable
fun RegisterOrLogin(
    navigateTo: () -> Unit,
    @StringRes subtitle: Int,
    @StringRes title: Int,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 20.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Row {
            Text(
                modifier = Modifier.align(Alignment.CenterVertically),
                text = stringResource(subtitle),
                style = VisbyTypography.subtitle1,
                color = Neutral7
            )
            ClickableText(
                AnnotatedString(stringResource(title)),
                style = TextStyle(
                    color = SystemColor,
                    fontStyle = VisbyTypography.subtitle1.fontStyle,
                    fontFamily = VisbyFontFamily,
                    fontWeight = FontWeight.Medium,
                    letterSpacing = 0.024.sp,
                    fontSize = VisbyTypography.subtitle1.fontSize
                ),
                modifier = Modifier
                    .padding(start = 4.dp)
                    .align(Alignment.CenterVertically),
                onClick = {
                    navigateTo()
                })
        }
    }
}