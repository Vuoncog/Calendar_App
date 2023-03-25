package com.example.taskmanagementapp.ui.theme.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskmanagementapp.ui.theme.PrimaryButton
import com.example.taskmanagementapp.ui.theme.VisbyFontFamily

@Composable
fun HomeContent() {

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainTask() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Column() {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "In progress",
                    color = PrimaryButton
                )
                Deadline()
            }
            Row()
            {
                Text(
                    text = "Todo App Mobile Design with Android Kotlin",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = VisbyFontFamily,
                    maxLines = 2
                )
            }
            Text(text = "The subcription")

            Row() {
                CompositionLocalProvider(LocalMinimumTouchTargetEnforcement provides false) {
                    Checkbox(
                        checked = false,
                        onCheckedChange = {},
                    )
                }
                Text(text = "Figma", modifier = Modifier.padding(start = 32.dp))
            }

            Row(horizontalArrangement = Arrangement.spacedBy(32.dp)) {
                CompositionLocalProvider(LocalMinimumTouchTargetEnforcement provides false) {
                    Checkbox(
                        checked = false,
                        onCheckedChange = {},
                    )
                }
                Text(text = "Android Studio")
            }
        }
    }
}

@Composable
fun Deadline() {
    Row() {
        Icon(imageVector = Icons.Filled.Add, contentDescription = "")
        Text(text = "9:00 AM")
    }
}

@Preview
@Composable
fun HomeContentPreview() {
    MainTask()
}