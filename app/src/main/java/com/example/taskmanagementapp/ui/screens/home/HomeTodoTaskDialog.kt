package com.example.taskmanagementapp.ui.screens.home

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.taskmanagementapp.R
import com.example.taskmanagementapp.constant.SubTask
import com.example.taskmanagementapp.constant.ToDoTask
import com.example.taskmanagementapp.data.SharedViewModel
import com.example.taskmanagementapp.ui.screens.profile.bottomsheet.CloseIcon
import com.example.taskmanagementapp.ui.theme.Neutral1
import com.example.taskmanagementapp.ui.theme.Neutral2
import com.example.taskmanagementapp.ui.theme.Primary4
import com.example.taskmanagementapp.ui.theme.VisbyTypography
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun HomeTodoTaskDialog(
    openDialogCustom: MutableState<Boolean>,
    systemColor: Color,
    toDoTask: ToDoTask,
    listSubTask: List<SubTask>,
) {
    Dialog(
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        ),
        onDismissRequest = {
            openDialogCustom.value = !openDialogCustom.value
        }) {
        HomeTodoTaskDialogUI(
            systemColor = systemColor,
            openDialogCustom = openDialogCustom,
            toDoTask = toDoTask,
            listSubTask = listSubTask,
        )
    }
}

@Composable
fun HomeTodoTaskDialogUI(
    systemColor: Color,
    openDialogCustom: MutableState<Boolean>,
    toDoTask: ToDoTask,
    listSubTask: List<SubTask>,
) {
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .padding(horizontal = 32.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(color = Color.White)
            .padding(
                horizontal = 12.dp,
                vertical = 16.dp
            ),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        HomeTodoTaskDialogTitle(
            systemColor = systemColor,
            onCloseClicked = {
                openDialogCustom.value = !openDialogCustom.value
            },
            content = toDoTask.taskName
        )

        val formatter = SimpleDateFormat("h:mm aa", Locale.ENGLISH)
        HomeTodoTaskDialogInfo(
            systemColor = systemColor,
            icon = R.drawable.ic_clock_circle,
            content = formatter.format(Date(toDoTask.time*1000))
        )

        HomeTodoTaskDialogInfo(
            systemColor = systemColor,
            icon = R.drawable.ic_detail,
            content = toDoTask.taskType.description
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            HomeTodoTaskDialogInfo(
                systemColor = systemColor,
                icon = R.drawable.ic_subtasks,
                content = "Subtasks"
            )
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                listSubTask.forEach {
                    HomeTodoTaskDialogSubtask(
                        systemColor = systemColor,
                        isCheck = mutableStateOf(it.done),
                        subTaskName = it.title
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeTodoTaskDialogTitle(
    systemColor: Color = Primary4,
    onCloseClicked: () -> Unit,
    content: String
) {
    CompositionLocalProvider(LocalMinimumInteractiveComponentEnforcement provides false) {
        Row(
            verticalAlignment = CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = content,
                style = VisbyTypography.subtitle1,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .padding(start = 24.dp)
                    .weight(1f),
                textAlign = TextAlign.Center,
                color = Neutral2
            )

            CloseIcon(
                systemColor = systemColor,
                onCloseClicked = onCloseClicked
            )
        }
    }
}

@Composable
fun HomeTodoTaskDialogSubtask(
    systemColor: Color,
    isCheck: MutableState<Boolean>,
    subTaskName: String
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(32.dp),
        verticalAlignment = CenterVertically,
        modifier = Modifier.padding(
            start = 48.dp,
            top = 4.dp,
        )
    ) {
        Card(
            modifier = Modifier.background(Color.White),
            elevation = 0.dp,
            shape = CircleShape,
            border = BorderStroke(1.dp, color = systemColor)
        ) {
            Box(
                modifier = Modifier
                    .size(18.dp)
                    .background(if (isCheck.value) systemColor else Color.White)
                    .clickable {
                        isCheck.value = !isCheck.value
                    },
                contentAlignment = Center
            ) {
                if(isCheck.value)
                    Icon(Icons.Default.Check, contentDescription = "", tint = Color.White)
            }
        }

        Text(
            text = subTaskName,
            style = VisbyTypography.subtitle2,
            color = Neutral1,
            modifier = Modifier.weight(1f),
        )
    }
}

@Composable
fun HomeTodoTaskDialogInfo(
    systemColor: Color,
    @DrawableRes icon: Int,
    content: String
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = CenterVertically
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = icon),
            contentDescription = "Icon",
            tint = systemColor
        )
        Text(
            text = content,
            style = VisbyTypography.subtitle1,
            color = Neutral1,
            modifier = Modifier.weight(1f)
        )
    }
}