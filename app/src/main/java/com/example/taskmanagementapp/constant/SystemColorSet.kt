package com.example.taskmanagementapp.constant

import androidx.compose.ui.graphics.Color
import com.example.taskmanagementapp.R
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

val listBoarSticker = listOf(
    R.drawable.boar_emoji_amazed,
    R.drawable.boar_emoji_angry,
    R.drawable.boar_emoji_annoyed,
    R.drawable.boar_emoji_confused,
    R.drawable.boar_emoji_cool,
    R.drawable.boar_emoji_dizzy,
    R.drawable.boar_emoji_hi,
    R.drawable.boar_emoji_lol,
    R.drawable.boar_emoji_love,
    R.drawable.boar_emoji_pissed,
    R.drawable.boar_emoji_puke,
    R.drawable.boar_emoji_sad,
    R.drawable.boar_emoji_scared,
    R.drawable.boar_emoji_shocked,
    R.drawable.boar_emoji_shy,
    R.drawable.boar_emoji_sick,
    R.drawable.boar_emoji_sleep,
    R.drawable.boar_emoji_smile,
    R.drawable.boar_emoji_smirk,
    R.drawable.boar_emoji_yawn,
)
val listBunnySticker = listOf(
    R.drawable.bunny_emoji_amazed,
    R.drawable.bunny_emoji_angry,
    R.drawable.bunny_emoji_annoyed,
    R.drawable.bunny_emoji_confused,
    R.drawable.bunny_emoji_cool,
    R.drawable.bunny_emoji_dizzy,
    R.drawable.bunny_emoji_hi,
    R.drawable.bunny_emoji_lol,
    R.drawable.bunny_emoji_love,
    R.drawable.bunny_emoji_pissed,
    R.drawable.bunny_emoji_puke,
    R.drawable.bunny_emoji_sad,
    R.drawable.bunny_emoji_scared,
    R.drawable.bunny_emoji_shocked,
    R.drawable.bunny_emoji_shy,
    R.drawable.bunny_emoji_sick,
    R.drawable.bunny_emoji_sleep,
    R.drawable.bunny_emoji_smile,
    R.drawable.bunny_emoji_smirk,
    R.drawable.bunny_emoji_yawn,
)