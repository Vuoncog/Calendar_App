package com.example.taskmanagementapp.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.taskmanagementapp.ui.theme.SystemColor

@Composable
fun IconType(
    @DrawableRes icon: Int,
    systemColor: Color = SystemColor
) {
    Box(
        modifier = Modifier
            .size(32.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(color = Color.White)
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = icon),
            contentDescription = "Task type",
            modifier = Modifier
                .size(16.dp)
                .align(Alignment.Center),
            tint = systemColor
        )
    }
}