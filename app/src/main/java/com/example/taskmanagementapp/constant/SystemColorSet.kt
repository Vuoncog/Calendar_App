package com.example.taskmanagementapp.constant

import androidx.compose.ui.graphics.Color
import com.example.taskmanagementapp.ui.theme.BackgroundColorTask
import com.example.taskmanagementapp.ui.theme.Primary4

enum class SystemColorSet(
    val primaryColor: Color,
    val secondaryColor: Color,
    val listStickerSet: List<Int>
) {
    ORANGE(
        primaryColor = Primary4,
        secondaryColor = BackgroundColorTask,
        listStickerSet = listBoarSticker
    ),
    GREEN(
        primaryColor = RecommendColor.Green.primary,
        secondaryColor = RecommendColor.Green.secondary,
        listStickerSet = listBunnySticker
    ),
    PURPLE(
        primaryColor = RecommendColor.Purple.primary,
        secondaryColor = RecommendColor.Purple.secondary,
        listStickerSet = listBunnySticker
    )
}
