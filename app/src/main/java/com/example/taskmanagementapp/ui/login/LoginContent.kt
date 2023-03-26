package com.example.taskmanagementapp.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskmanagementapp.R
import com.example.taskmanagementapp.ui.theme.*
import com.example.taskmanagementapp.ui.component.CustomButton
import com.example.taskmanagementapp.ui.component.CustomOutlinedButton
import com.example.taskmanagementapp.ui.component.CustomTextField

@Composable
fun LoginContent() {
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
        CustomTextField(isPassword = false)
        CustomTextField(isPassword = true)
        ClickableText(
            modifier = Modifier.fillMaxWidth(),
            text = AnnotatedString("Recover password"),
            onClick = {

            },
            style = VisbyTypography.subtitle1
        )

        //button LOG IN
        CustomButton(buttonText = "Log in")

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
        CustomOutlinedButton(whichPlatform = "Google")
        CustomOutlinedButton(whichPlatform = "Facebook")

        //Register
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 20.dp),
            contentAlignment = BottomCenter
        ) {
            Row {
                Text(
                    modifier = Modifier.align(CenterVertically),
                    text = "Not a member?",
                    style = VisbyTypography.subtitle1,
                    color = Neutral7
                )
                ClickableText(
                    AnnotatedString("Register now"),
                    style = TextStyle(
                        color = Primary4,
                        fontStyle = VisbyTypography.subtitle1.fontStyle,
                        fontFamily = VisbyFontFamily,
                        fontWeight = FontWeight.Medium,
                        letterSpacing = 0.024.sp,
                        fontSize = VisbyTypography.subtitle1.fontSize
                    ),
                    modifier = Modifier
                        .padding(start = 4.dp)
                        .align(CenterVertically),
                    onClick = {

                    })
            }
        }
    }
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
                painter = painterResource(id = R.drawable.star),
                contentDescription = "Star"
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
    LoginContent()
}