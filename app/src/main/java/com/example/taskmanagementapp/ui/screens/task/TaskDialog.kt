package com.example.taskmanagementapp.ui.screens.task

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.taskmanagementapp.R
import com.example.taskmanagementapp.data.SharedViewModel
import com.example.taskmanagementapp.ui.theme.Neutral2
import com.example.taskmanagementapp.ui.theme.Primary4
import com.example.taskmanagementapp.ui.theme.SystemColor
import com.example.taskmanagementapp.ui.theme.VisbyTypography


@Composable
fun RemoveIcon(
    systemColor: Color = SystemColor,
    onClicked: () -> Unit,
    sharedViewModel: SharedViewModel
) {
    val showDialog = remember { mutableStateOf(false) }
    IconButton(onClick = {
        showDialog.value = true
        Log.d("Show dialog", showDialog.value.toString())
    }) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_bin),
            contentDescription = "Close icon",
            tint = systemColor,
            modifier = Modifier.size(28.dp)
        )
    }
    if (showDialog.value) {
        CustomDialog(
            systemColor = systemColor,
            openDialogCustom = showDialog,
            onClicked = onClicked,
            sharedViewModel = sharedViewModel
        )
    }
}

@Composable
fun CustomDialog(
    systemColor: Color,
    openDialogCustom: MutableState<Boolean>,
    onClicked: () -> Unit,
    sharedViewModel: SharedViewModel
) {
    Dialog(
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        ),
        onDismissRequest = {
            openDialogCustom.value = !openDialogCustom.value
        }) {
        CustomDialogUI(
            systemColor = systemColor,
            openDialogCustom = openDialogCustom,
            onClicked = onClicked,
            sharedViewModel = sharedViewModel
        )
    }
}

@Composable
fun CustomDialogUI(
    systemColor: Color,
    openDialogCustom: MutableState<Boolean>,
    onClicked: () -> Unit,
    sharedViewModel: SharedViewModel
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
        DialogTitle(
            systemColor = systemColor,
            onBackClicked = {
                openDialogCustom.value = !openDialogCustom.value
            }
        )

        DialogContent(contentText = sharedViewModel.oldEventInfo.title, isEvent = false)

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(top = 8.dp)
        ) {
            DialogButton(
                isConfirm = false,
                modifier = Modifier.weight(1f),
                onClicked = {
                    openDialogCustom.value = !openDialogCustom.value
                }
            )

            DialogButton(
                isConfirm = true,
                modifier = Modifier.weight(1f),
                onClicked = {
                    openDialogCustom.value = !openDialogCustom.value
                    onClicked()
                }
            )
        }
    }
}

@Composable
fun DialogButton(
    isConfirm: Boolean = true,
    modifier: Modifier,
    onClicked: () -> Unit,
) {
    Button(
        modifier = Modifier
            .padding(0.dp)
            .height(48.dp)
            .then(modifier),
        onClick = {
            onClicked()
        },
        border = if (!isConfirm) BorderStroke(1.dp, SystemColor) else null,
        colors =
        if (isConfirm) ButtonDefaults.buttonColors(
            backgroundColor = SystemColor,
            contentColor = Color.White,
            disabledBackgroundColor = SystemColor.copy(alpha = 0.4f),
            disabledContentColor = Color.White,
        )
        else ButtonDefaults.outlinedButtonColors(
            contentColor = Neutral2,
            backgroundColor = Color.Transparent,
            disabledContentColor = SystemColor.copy(alpha = 0.4f)
        ),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
            disabledElevation = 0.dp,
            hoveredElevation = 0.dp,
            focusedElevation = 0.dp
        ),
        shape = RoundedCornerShape(40.dp),
    ) {
        Text(
            text = if (isConfirm) "Confirm".uppercase()
            else "Cancel".uppercase(),
            style = VisbyTypography.button,
        )
    }
}

@Composable
fun DialogTitle(
    systemColor: Color = Primary4, onBackClicked: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()
    ) {
        AddTaskBackIcon(
            systemColor = systemColor,
            onBackClicked = onBackClicked,
            size = 24.dp
        )

        Text(
            text = "Are you sure ?",
            style = VisbyTypography.subtitle1,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .padding(end = 32.dp)
                .weight(1f)
                .align(Alignment.CenterVertically),
            textAlign = TextAlign.Center,
            color = Neutral2
        )
    }
}

@Composable
fun DialogContent(
    contentText: String,
    isEvent: Boolean,
) {
    Text(
        buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = Neutral2,
                    fontStyle = VisbyTypography.subtitle1.fontStyle,
                    fontWeight = FontWeight.Normal,
                    fontSize = VisbyTypography.subtitle1.fontSize
                )
            ) {
                append(
                    if (isEvent) "Do you want to delete event "
                    else "Do you want to delete Todo-task "
                )
            }
            withStyle(
                style = SpanStyle(
                    color = Neutral2,
                    fontStyle = VisbyTypography.subtitle1.fontStyle,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = VisbyTypography.subtitle1.fontSize
                )
            ) {
                append(contentText)
            }
        }, modifier = Modifier.padding(12.dp)
    )
}

