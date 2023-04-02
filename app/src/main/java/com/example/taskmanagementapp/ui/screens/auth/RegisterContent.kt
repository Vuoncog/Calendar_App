package com.example.taskmanagementapp.ui.screens.auth

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskmanagementapp.R
import com.example.taskmanagementapp.ViewModel.LogInViewModel
import com.example.taskmanagementapp.ui.component.CustomButton
import com.example.taskmanagementapp.ui.component.CustomTextField
import com.example.taskmanagementapp.ui.component.RegisterOrLogin
import com.example.taskmanagementapp.ui.theme.Neutral6
import com.example.taskmanagementapp.ui.theme.Neutral7
import com.example.taskmanagementapp.ui.theme.VisbyTypography
import kotlin.math.log

@Composable
fun RegisterContent(
    navigateToLogin: () -> Unit,
    logInViewModel: LogInViewModel? = null
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(id = R.drawable.login_background),
        contentDescription = "Background",
        contentScale = ContentScale.FillHeight
    )
    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier.padding(
            top = 72.dp,
            start = 16.dp,
            end = 16.dp,
            bottom = 0.dp
        )
    ) {
        //Login title
        Register()

        //Text field
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            name = CustomTextField(
                isPassword = false,
                leadingIcon = R.drawable.ic_person_solid,
                title = stringResource(R.string.full_name)
            )
            email = CustomTextField(
                isPassword = false,
                leadingIcon = R.drawable.ic_user_circle,
                title = stringResource(R.string.gmail)
            )
            password = CustomTextField(isPassword = true)
        }

        Attention()

        //button REGISTER
        CustomButton(
            buttonText = stringResource(R.string.register),
            logInViewModel = logInViewModel,
            navigateToHome = navigateToLogin,
            onClickEvent = {logInViewModel?.signUp(email, password, name)})

        //Login
        RegisterOrLogin(
            navigateTo = navigateToLogin,
            subtitle = R.string.haved_account,
            title = R.string.login_now
        )
    }
}

@Composable
fun Register() {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(
                text = stringResource(R.string.create_account),
                style = VisbyTypography.h5
            )

            Image(
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.CenterVertically),
                painter = painterResource(id = R.drawable.star),
                contentDescription = "Star"
            )
        }

        Text(
            text = stringResource(R.string.register_subsciption),
            style = VisbyTypography.subtitle1,
            color = Neutral7
        )
    }
}

@Composable
fun Attention() {
    val attentionList = listOf(
        "At least 6 characters",
        "Both uppercase and lowercase (optional)",
        "At least one number or symbol (optional)",
    )
    LazyColumn {
        items(attentionList) { item ->
            AttentionItem(title = item)
        }
    }
}

@Composable
fun AttentionItem(title: String) {
    Row(modifier = Modifier.padding(start = 16.dp)) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_chevron_right),
            contentDescription = "Attention Icon",
            tint = Neutral6,
            modifier = Modifier
                .size(12.dp)
                .align(CenterVertically)
        )

        Text(
            text = title,
            style = VisbyTypography.caption,
            color = Neutral6,
            modifier = Modifier
                .padding(start = 4.dp)
                .align(CenterVertically)
        )
    }
}

@Composable
@Preview
fun RegisterContentPreview() {
    RegisterContent({})
}