package com.example.taskmanagementapp.ui.screens.management

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.taskmanagementapp.constant.ToDoTask
import com.example.taskmanagementapp.ui.component.IconType
import com.example.taskmanagementapp.ui.component.Tick
import com.example.taskmanagementapp.ui.theme.Neutral2
import com.example.taskmanagementapp.ui.theme.VisbyTypography

@Composable
fun TaskState(
    systemColor: Color,
    subSystemColor: Color,
    listTask: List<ToDoTask>,
    isCompleted: Boolean,
    changeTaskState: (ToDoTask, ToDoTask) -> Unit,
    navigateToUpdateTask: (toDoTask: ToDoTask) -> Unit
) {
    val statusTitle = if (isCompleted) "Completed" else "Not started"

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = statusTitle,
            style = VisbyTypography.h6,
            color = Neutral2
        )

        /*LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(bottom = 50.dp)
        ) {
            items(items = listTask) { toDoTask ->
                if (toDoTask.getDone() == isCompleted) {
                    TaskDisplay(
                        toDoTask = toDoTask,
                        onChangeStateClicked = changeTaskState,
                        systemColor = systemColor,
                        subSystemColor = subSystemColor,
                        navigateToUpdateTask = navigateToUpdateTask
                    )
                }
            }
        }*/

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .verticalScroll(state = rememberScrollState())
                .padding(bottom = if(isCompleted) 60.dp else 0.dp)
        ) {
            listTask.forEach { task ->
                if (task.getDone() == isCompleted) {
                    TaskDisplay(
                        toDoTask = task,
                        onChangeStateClicked = changeTaskState,
                        systemColor = systemColor,
                        subSystemColor = subSystemColor,
                        navigateToUpdateTask = navigateToUpdateTask
                    )
                }
            }
        }
    }
}

@Composable
fun TaskDisplay(
    toDoTask: ToDoTask,
    onChangeStateClicked: (ToDoTask, ToDoTask) -> Unit,
    systemColor: Color,
    subSystemColor: Color,
    navigateToUpdateTask: (toDoTask: ToDoTask) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .clickable { navigateToUpdateTask(toDoTask) }
            .clip(RoundedCornerShape(8.dp))
            .background(color = subSystemColor)
            .padding(8.dp),
        verticalAlignment = CenterVertically
    ) {
        IconType(
            icon = toDoTask.taskType.icon,
            systemColor = systemColor
        )
        Text(
            text = toDoTask.taskName,
            style = VisbyTypography.subtitle1,
            color = systemColor,
            modifier = Modifier.weight(1f)
        )
        Tick(
            toDoTask = toDoTask,
            systemColor = systemColor,
            onChangeStateClicked = onChangeStateClicked,
        )

    }

}