package com.example.taskmanagementapp.ui.screens.task

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.taskmanagementapp.constant.listBreakfast
import com.example.taskmanagementapp.constant.listSticker
import com.example.taskmanagementapp.constant.listTaskType
import com.example.taskmanagementapp.ui.screens.profile.bottomsheet.CloseIcon
import com.example.taskmanagementapp.ui.theme.*


@Composable
fun Sticker(
    systemColor: Color = SystemColor,
    subSystemColor: Color,
    isEvent: Boolean,
    onIconClicked: (Int) -> Unit,
    @DrawableRes sticker: MutableState<Int>
) {
    val showDialog = remember { mutableStateOf(false) }

    if (isEvent) {
        Image(
            imageVector = ImageVector.vectorResource(id = sticker.value),
            contentDescription = "Add",
            modifier = Modifier
                .size(24.dp)
                .clickable {
                    showDialog.value = !showDialog.value
                }
        )
    } else {
        Icon(
            imageVector = ImageVector.vectorResource(id = sticker.value),
            contentDescription = "Add",
            tint = systemColor,
            modifier = Modifier
                .size(24.dp)
                .clickable {
                    showDialog.value = !showDialog.value
                }
        )
    }

    if (showDialog.value) {
        CustomStickerDialog(
            isEvent = isEvent,
            systemColor = systemColor,
            subSystemColor = subSystemColor,
            openDialogCustom = showDialog,
            onIconClicked = onIconClicked,
        )
    }
}

@Composable
fun CustomStickerDialog(
    isEvent: Boolean,
    systemColor: Color,
    subSystemColor: Color,
    openDialogCustom: MutableState<Boolean>,
    onIconClicked: (Int) -> Unit,
) {
    Dialog(
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        ),
        onDismissRequest = {
            openDialogCustom.value = !openDialogCustom.value
        }) {
        CustomStickerDialogUI(
            isEvent = isEvent,
            systemColor = systemColor,
            subSystemColor = subSystemColor,
            openDialogCustom = openDialogCustom,
            onIconClicked = onIconClicked,
//            sharedViewModel = sharedViewModel
        )
    }
}

@Composable
fun CustomStickerDialogUI(
    isEvent: Boolean,
    systemColor: Color,
    subSystemColor: Color,
    openDialogCustom: MutableState<Boolean>,
    onIconClicked: (Int) -> Unit,
//    sharedViewModel: SharedViewModel
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .padding(
                start = 16.dp,
                end = 16.dp,
                bottom = 12.dp
            ),
    ) {
        StickerTitle(
            systemColor = systemColor,
            onCloseClicked = {
                openDialogCustom.value = !openDialogCustom.value
            }
        )

        TaskContent(
            isEvent = isEvent,
            systemColor = systemColor,
            subSystemColor = subSystemColor,
            onIconClicked = onIconClicked,
            openDialogCustom = openDialogCustom
        )
    }
}

@Composable
fun StickerTitle(
    systemColor: Color = Primary4,
    onCloseClicked: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Choose sticker",
            style = VisbyTypography.subtitle1,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .padding(start = 32.dp)
                .weight(1f)
                .align(Alignment.CenterVertically),
            textAlign = TextAlign.Center,
            color = Neutral2
        )

        CloseIcon(
            systemColor = systemColor,
            onCloseClicked = onCloseClicked
        )
    }
}

@Composable
fun TaskContent(
    isEvent: Boolean,
    systemColor: Color,
    subSystemColor: Color,
    onIconClicked: (Int) -> Unit,
    openDialogCustom: MutableState<Boolean>,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        if (isEvent) {
            ListEventSticker(
                listIcon = listSticker,
                onIconClicked = onIconClicked,
                openDialogCustom = openDialogCustom
            )
        } else {
            ListToDoIcon(
                listIcon = listTaskType,
                listName = "General",
                systemColor = systemColor,
                subSystemColor = subSystemColor,
                onIconClicked = onIconClicked,
                openDialogCustom = openDialogCustom
            )
            ListToDoIcon(
                listIcon = listBreakfast,
                listName = "Breakfast",
                systemColor = systemColor,
                subSystemColor = subSystemColor,
                onIconClicked = onIconClicked,
                openDialogCustom = openDialogCustom
            )
        }
    }

}

@Composable
fun ListEventSticker(
    listIcon: List<Int>,
    onIconClicked: (Int) -> Unit,
    openDialogCustom: MutableState<Boolean>,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(count = 5),
        modifier = Modifier
            .padding(bottom = 8.dp)
    ) {
        items(listIcon) {
            Box {
                Image(
                    imageVector = ImageVector.vectorResource(id = it),
                    contentDescription = "Event Sticker",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(bottom = 12.dp)
                        .size(48.dp)
                        .clickable {
                            onIconClicked(it)
                            openDialogCustom.value = !openDialogCustom.value
                        }
                )
            }
        }
    }
}

@Composable
fun ListToDoIcon(
    listIcon: List<Int>,
    listName: String,
    systemColor: Color,
    subSystemColor: Color,
    onIconClicked: (Int) -> Unit,
    openDialogCustom: MutableState<Boolean>,
) {

    Text(
        text = listName,
        style = VisbyTypography.subtitle1,
        color = Neutral2
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(count = 5),
        modifier = Modifier
            .padding(bottom = 8.dp)
    ) {
        items(listIcon) {
            Box() {
                Icon(
                    imageVector = ImageVector.vectorResource(id = it),
                    contentDescription = "Sticker",
                    tint = systemColor,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(
                            bottom = 12.dp
                        )
                        .clip(RoundedCornerShape(12.dp))
                        .size(48.dp)
                        .background(color = subSystemColor)
                        .padding(12.dp)
                        .clickable {
                            onIconClicked(it)
                            openDialogCustom.value = !openDialogCustom.value
                        }
                )
            }
        }
    }
}

@Preview
@Composable
fun IconPreview() {
    CustomStickerDialogUI(
        isEvent = false,
        subSystemColor = BackgroundColorTask,
        openDialogCustom = mutableStateOf(false),
        onIconClicked = { /*TODO*/ },
        systemColor = Primary4
    )
}

@Preview
@Composable
fun StickerPreview() {
    CustomStickerDialogUI(
        isEvent = true,
        subSystemColor = BackgroundColorTask,
        openDialogCustom = mutableStateOf(false),
        onIconClicked = { /*TODO*/ },
        systemColor = Primary4
    )
}