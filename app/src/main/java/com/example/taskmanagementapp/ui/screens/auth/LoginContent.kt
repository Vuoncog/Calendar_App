package com.example.taskmanagementapp.ui.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskmanagementapp.R
import com.example.taskmanagementapp.ViewModel.LogInViewModel
import com.example.taskmanagementapp.ui.theme.*
import com.example.taskmanagementapp.ui.component.CustomButton
import com.example.taskmanagementapp.ui.component.CustomOutlinedButton
import com.example.taskmanagementapp.ui.component.CustomTextField
import com.example.taskmanagementapp.ui.component.RegisterOrLogin

@Composable
fun LoginContent(
    navigateToHome: () -> Unit,
    navigateToRecoverPassword: () -> Unit,
    navigateToRegister: () -> Unit,
    logInViewModel: LogInViewModel? = null
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val currentUser = logInViewModel?.getCurrentUser()

    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(id = R.drawable.login_background),
        contentDescription = "Login Background",
        contentScale = ContentScale.FillHeight
    )
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.padding(
            top = 72.dp,
            start = 16.dp,
            end = 16.dp,
            bottom = 0.dp
        )
    ) {
        //Login title
        Login()

        //Text field
        email = CustomTextField(
            isPassword = false,
            leadingIcon = R.drawable.ic_user_circle,
            title = "Gmail",
            currentEmail = if (currentUser != null) currentUser.email.toString() else ""
        )
        password = CustomTextField(isPassword = true)
        ClickableText(
            modifier = Modifier.fillMaxWidth(),
            text = AnnotatedString(stringResource(R.string.recover_password)),
            onClick = {
                navigateToRecoverPassword()
            },
            style = VisbyTypography.subtitle1
        )

        //button LOG IN
        CustomButton(
            navigateToHome = navigateToHome,
            buttonText = stringResource(R.string.login),
            onClickEvent = {logInViewModel?.signIn(email, password, navigateToHome)},
            logInViewModel = logInViewModel,

        )

        Text(
            text = "Or continue with",
            style = VisbyTypography.subtitle1,
            color = Neutral4,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            textAlign = TextAlign.Center
        )

        // Other platform
        CustomOutlinedButton(
            navigateToHome,
            whichPlatform = "Google"
        )
        CustomOutlinedButton(
            navigateToHome,
            whichPlatform = "Facebook"
        )

        //Register
        RegisterOrLogin (
            navigateTo = navigateToRegister,
            subtitle = R.string.not_a_member,
            title = R.string.register_now
        )
    }

    logInViewModel?.signOut()
}

@Composable
fun Login() {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(
                text = "Login",
                style = VisbyTypography.h5
            )

            Image(
                modifier = Modifier
                    .size(24.dp)
                    .align(CenterVertically),
                painter = painterResource(id = R.drawable.happy_star),
                contentDescription = "Happy Star"
            )
        }

        Text(
            text = "Welcome to my app",
            style = VisbyTypography.subtitle1,
            color = Neutral7
        )
    }
}

@Preview
@Composable
fun LoginContentPreview() {
    LoginContent({}, {}, {})
}