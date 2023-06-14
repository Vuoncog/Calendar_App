package com.example.taskmanagementapp.ui.screens.task

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.example.taskmanagementapp.R
import com.example.taskmanagementapp.constant.SystemColorSet
import com.example.taskmanagementapp.constant.listTaskType
import com.example.taskmanagementapp.ui.theme.Neutral2
import com.example.taskmanagementapp.ui.theme.Neutral8
import com.example.taskmanagementapp.ui.theme.VisbyTypography

@Composable
fun Sticker(
    systemColor: Color = SystemColorSet.ORANGE.primaryColor,
    onClicked: () -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }
    IconButton(onClick = {
        showDialog = !showDialog
    }) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_remove),
            contentDescription = "Close icon",
            tint = systemColor,
            modifier = Modifier.size(28.dp)
        )
    }
    if (showDialog) {
        AlertDialog(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .clip(RoundedCornerShape(16.dp)),
            properties = DialogProperties(
                usePlatformDefaultWidth = false
            ),
            onDismissRequest = { showDialog = !showDialog },
            title = {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Choose sticker",
                        color = systemColor,
                        style = VisbyTypography.subtitle1,
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .weight(1f),
                        fontWeight = FontWeight.SemiBold
                    )
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_remove),
                        contentDescription = "Remove",
                        tint = Neutral2,
                        modifier = Modifier
                            .size(32.dp)
                            .clickable {
                                showDialog = !showDialog
                            }
                    )
                }

            },
            text = {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(5),
                ) {
                    items(listTaskType) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = it.icon),
                            contentDescription = "Sticker",
                            tint = Neutral2,
                            modifier = Modifier
                                .padding(
                                    start = 8.dp,
                                    end = 8.dp,
                                    bottom = 12.dp
                                )
                                .shadow(
                                    elevation = 2.dp,
                                    shape = RoundedCornerShape(14.dp)
                                )
                                .clip(RoundedCornerShape(14.dp))
                                .size(48.dp)
                                .background(color = Neutral8)
                                .padding(13.dp)
                                .clickable {
                                    showDialog = !showDialog
                                }
                        )
                    }
                }
            },
            confirmButton = {

            },
        )
    }
}

@Preview
@Composable
fun StickerPreview() {
    Sticker(
        onClicked = {}
    )
}