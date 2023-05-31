package com.example.taskmanagementapp.ui.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.taskmanagementapp.R
import com.example.taskmanagementapp.constant.TaskType
import com.example.taskmanagementapp.ui.theme.Neutral5
import com.example.taskmanagementapp.ui.theme.VisbyTypography

@Composable
fun TodoTask(
    list: List<TaskType>,
    systemColor: Color,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "${list.size} task completed".uppercase(),
                style = VisbyTypography.subtitle2,
                color = Neutral5,
                modifier = Modifier.weight(1f)
            )
            Text(text = stringResource(R.string.view_all),
                style = VisbyTypography.button,
                color = systemColor,
                modifier = Modifier.clickable {

                }
                    .padding(end = 16.dp))
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.horizontalScroll(
                state = rememberScrollState()
            )
        ) {
            list.forEach { task -> HomeTodoTaskItem(taskType = task) }
        }
    }
}