package com.drive.authorizationPage.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.drive.authorizationPage.mvi.AuthorizationIntent
import com.drive.authorizationPage.mvi.AuthorizationNavIntent
import com.drive.authorizationPage.mvi.AuthorizationState
import com.drive.authorizationPage.mvi.AuthorizationViewModel
import com.drive.ui.theme.primaryColor
import com.drive.ui.theme.whiteBackground


@Composable
fun AuthorizationScreen(
    state: AuthorizationState,
    viewModel: AuthorizationViewModel
) {


    val passwordVisibility = remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter) {

        Box(modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
            contentAlignment = Alignment.TopCenter) {

            Image(painter = painterResource(
                id = com.drive.R.drawable.login_image),
                contentDescription = "Login image",
                modifier = Modifier.fillMaxWidth())
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.60f)
                .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                .background(whiteBackground)
                .padding(16.dp)
        ) {
            Text(text = "Вход в систему", style = TextStyle(
                fontWeight = FontWeight.Bold,
                letterSpacing = 2.sp
            ),
                fontSize = 30.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.padding(20.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                OutlinedTextField(
                    value = state.email,
                    onValueChange = {viewModel.publishIntent(AuthorizationIntent.ChangeEmail(email = it))},
                    label = { Text(text = "Email Address")},
                    placeholder = { Text(text = "Email Address")},
                    singleLine = true,
                    modifier = Modifier
                        .height(56.dp)
                        .fillMaxWidth(0.8f)
                )

                OutlinedTextField(
                    value = state.password,
                    onValueChange = {viewModel.publishIntent(AuthorizationIntent.ChangePassword(it)) },
                    trailingIcon = {
                        IconButton(onClick = {
                            passwordVisibility.value = !passwordVisibility.value
                        }) {

                            Icon(painter = painterResource(
                                id = com.drive.R.drawable.password_eye),
                                contentDescription = "Скрыть пароль",
                                tint = if (passwordVisibility.value) primaryColor else Color.Gray
                            )

                        }
                    },
                    label = { Text(text = "Password")},
                    placeholder = { Text(text = "Password")},
                    singleLine = true,
                    visualTransformation = if (passwordVisibility.value) VisualTransformation.None
                    else PasswordVisualTransformation(),
                    modifier = Modifier
                        .height(56.dp)
                        .fillMaxWidth(0.8f)
                )

                Spacer(modifier = Modifier.padding(10.dp))
                Button(onClick = {viewModel.navigateToScreen(AuthorizationNavIntent.GoToMainScreen)},
                    Modifier
                        .fillMaxWidth(0.8f)
                        .height(50.dp)) {
                    Text(
                        text = "Войти",
                        fontSize = 20.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Spacer(modifier = Modifier.padding(20.dp))
            }
        }
    }
}