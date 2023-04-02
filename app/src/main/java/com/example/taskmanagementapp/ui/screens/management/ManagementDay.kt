package com.example.taskmanagementapp.ui.screens.management

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun DateSelector(

) {
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = selectedDate.format(DateTimeFormatter.ISO_LOCAL_DATE),
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview
@Composable
fun Previewwe() {
    DateSelector()
}