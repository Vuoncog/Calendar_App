package com.example.taskmanagementapp.ui.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.taskmanagementapp.R
import com.example.taskmanagementapp.constant.TaskType
import com.example.taskmanagementapp.ui.theme.Neutral5
import com.example.taskmanagementapp.ui.theme.SystemColor
import com.example.taskmanagementapp.ui.theme.VisbyTypography

@Composable
fun TodoTask(
    list: List<TaskType>
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "1 task completed".uppercase(),
                style = VisbyTypography.subtitle2,
                color = Neutral5,
                modifier = Modifier.weight(1f)
            )
            Text(text = stringResource(R.string.view_all),
                style = VisbyTypography.button,
                color = SystemColor,
                modifier = Modifier.clickable {

                })
        }

        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(list) {
                TodoTaskItem(taskType = it)
            }
        }
    }
}