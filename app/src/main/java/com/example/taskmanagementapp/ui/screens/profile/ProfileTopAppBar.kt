package com.example.taskmanagementapp.ui.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskmanagementapp.R
import com.example.taskmanagementapp.ui.theme.Neutral2
import com.example.taskmanagementapp.ui.theme.VisbyFontFamily
import com.example.taskmanagementapp.ui.theme.VisbyTypography

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProfileTopAppBar() {
    ProfileAppBar()
}

@Composable
fun ProfileAppBar(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.image_2),
            contentDescription = "Image Top App Bar",
            contentScale = ContentScale.FillBounds
        )
    }

    TopAppBar(
        title = {
            Text(
                text = "Profile",
                style = VisbyTypography.h6
            )
        },
        actions = {
                  EditIcon()
        },
        backgroundColor = Color.Transparent,
        elevation = 0.dp
    )
}

@Composable
fun EditIcon(){
    IconButton(onClick = { /*TODO*/ }) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_edit),
            contentDescription = "Edit Icon",
            tint = Neutral2
        )
    }
}

@Composable
@Preview
fun ProfileTopAppBarPreview() {
    ProfileTopAppBar()
}

