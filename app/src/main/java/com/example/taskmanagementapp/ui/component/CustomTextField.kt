package com.example.taskmanagementapp.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskmanagementapp.R
import com.example.taskmanagementapp.ui.theme.*

@Composable
fun CustomTextField(
    isPassword: Boolean = true,
    @DrawableRes leadingIcon: Int = R.drawable.ic_password,
    title: String = "Password"
) {
    var passwordVisibility by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf("") }
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp)),
        visualTransformation = if (passwordVisibility || !isPassword) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        value = text,
        onValueChange = { it ->
            text = it
        },
        leadingIcon = {
                Icon(
                    imageVector = ImageVector.vectorResource(id = leadingIcon),
                    contentDescription = "Leading Icon",
                    tint = Primary5
                )
        },
        trailingIcon = {
            if (isPassword)
                IconButton(
                    onClick = {
                        passwordVisibility = !passwordVisibility
                    },
                ) {
                    if (passwordVisibility) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_show_password),
                            contentDescription = "Eye Icon",
                            tint = Neutral5,
                        )
                    } else {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_hide_password),
                            contentDescription = "Hided Eye Icon",
                            tint = Neutral5,
                        )
                    }
                }
        },
        textStyle = VisbyTypography.subtitle1,
        placeholder = {
            Text(
                modifier = Modifier.alpha(0.8f),
                text = title,
                style = VisbyTypography.subtitle1,
                color = Neutral5
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = BackgroundColorTask,
            textColor = Neutral2,
            disabledTextColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
    )
}

@Preview
@Composable
fun CustomTextFieldPreview() {
    CustomTextField()
}