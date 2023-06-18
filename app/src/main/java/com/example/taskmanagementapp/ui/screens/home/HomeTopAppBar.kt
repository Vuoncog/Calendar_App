package com.example.taskmanagementapp.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskmanagementapp.R
import com.example.taskmanagementapp.constant.loadImage
import com.example.taskmanagementapp.data.SharedViewModel
import com.example.taskmanagementapp.ui.theme.VisbyFontFamily

@Composable
fun HomeAppBar(sharedViewModel: SharedViewModel) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.image_1),
            contentDescription = "Image Top App Bar",
            contentScale = ContentScale.FillBounds
        )
    }

    TopAppBar(
        navigationIcon = {
            AvatarIcon(sharedViewModel = sharedViewModel)
        },
        title = {
            Text(
                text = stringResource(R.string.home),
                fontFamily = VisbyFontFamily,
                fontSize = MaterialTheme.typography.h6.fontSize,
                fontWeight = FontWeight.Medium,
            )
        },
        actions = {
            NotificationIcon()
        },
        backgroundColor = Color.Transparent,
        elevation = 0.dp
    )
}

@Composable
fun NotificationIcon() {
    IconButton(onClick = { /*TODO*/ }) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_notification),
            contentDescription = "Notification Icon"
        )
    }
}

@Composable
fun AvatarIcon(sharedViewModel: SharedViewModel) {
    val imageUser = loadImage(url = sharedViewModel.getCurrentUser()?.photoUrl.toString(), activity = sharedViewModel.mainActivity).value
    IconButton(
        modifier = Modifier
            .fillMaxSize(),
        onClick = { /*TODO*/ }) {
        /*Image(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape),
            painter = painterResource(id = R.drawable.avatar),
            contentDescription = "Avatar"
        )*/
        imageUser?.asImageBitmap()?.let { Image(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape),
            bitmap = it,
            contentDescription = "Avatar"
        ) }
    }
}

@Composable
@Preview
fun HomeTopAppBarPreview() {

}