package com.example.taskmanagementapp.ui.screens.home

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskmanagementapp.R
import com.example.taskmanagementapp.ViewModel.LogInViewModel
import com.example.taskmanagementapp.constant.TaskType
import com.example.taskmanagementapp.ui.component.IconType
import com.example.taskmanagementapp.ui.theme.*

@Composable
fun HomeContent(logInViewModel: LogInViewModel? = null) {
    val listTask = listOf(
        TaskType.Running,
        TaskType.Shopping,
        TaskType.PetFood,
        TaskType.WalkTheDog,
    )

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Column {
            Text(
                text = stringResource(R.string.home_first_title),
                style = VisbyTypography.h6,
                color = Neutral1
            )

            Text(
                text = "4 todo tasks",
                style = VisbyTypography.h6,
                color = SystemColor
            )
        }

        TodoTask(list = listTask)

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)
        , modifier = Modifier.padding(top = 12.dp)) {
            Text(
                text = stringResource(R.string.upcoming_events),
                style = VisbyTypography.subtitle1,
                color = Neutral3,
                fontWeight = FontWeight.SemiBold,
                letterSpacing = 1.sp
            )
            HomeEvent()
        }

    }

    logInViewModel?.signOut()
}



@Composable
fun MiniDetail(
    @DrawableRes icon: Int,
    title: String
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = icon),
            contentDescription = "Icon Detail",
            tint = SystemColor
        )

        Text(
            text = title,
            style = VisbyTypography.subtitle2,
            color = Neutral2
        )
    }
}

@Composable
fun TodoTaskItem(
    taskType: TaskType
) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(color = BackgroundColorTask)
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = CenterVertically
    ) {
        IconType(icon = taskType.icon)
        Column(
            modifier = Modifier
                .align(CenterVertically)
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = taskType.description,
                style = VisbyTypography.subtitle2,
                color = Neutral2
            )
            Text(
                text = "8:00 AM",
                style = VisbyTypography.overline,
                color = Neutral6
            )
        }

        Icon(
            modifier = Modifier
                .clickable {
                    /*TODO*/
                }
                .align(CenterVertically),
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_vertical_menu),
            contentDescription = "More information",
            tint = SystemColor
        )

    }
}

@Composable
@Preview
fun HomeContentPreview() {
    HomeContent()
}