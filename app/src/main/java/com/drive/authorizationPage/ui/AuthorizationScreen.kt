package com.drive.authorizationPage.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.drive.R
import com.drive.authorizationPage.mvi.AuthorizationIntent
import com.drive.authorizationPage.mvi.AuthorizationNavIntent
import com.drive.authorizationPage.mvi.AuthorizationState
import com.drive.authorizationPage.mvi.AuthorizationViewModel
import com.drive.ui.theme.*


@Composable
fun AuthorizationScreen(
    state: AuthorizationState,
    viewModel: AuthorizationViewModel
) {

    val focusManager = LocalFocusManager.current
    val passwordVisibility = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        myAuthBackColor,
                        myMainCarColor,
                        myLessonBackColor
                    )
                )
            )
    ) {

        Column {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .fillMaxHeight(0.75f)
                    .clip(RoundedCornerShape(30.dp))
                    .background(whiteBackground)
            ) {
                Spacer(modifier = Modifier.padding(10.dp))
                Image(
                    painter = painterResource(
                        id = R.drawable.login_image
                    ),
                    contentDescription = "Login image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
                Spacer(modifier = Modifier.padding(10.dp))
                Text(
                    text = "Вход в систему", style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 2.sp
                    ),
                    fontSize = 30.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.padding(10.dp))
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    OutlinedTextField(
                        value = state.email,
                        onValueChange = {
                            viewModel.publishIntent(AuthorizationIntent.ChangeEmail(email = it))
                        },
                        label = { Text(text = "Email Address") },
                        placeholder = { Text(text = "Email Address") },
                        singleLine = true,
                        modifier = Modifier
                            .height(56.dp)
                            .fillMaxWidth(0.8f),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = {focusManager.moveFocus(FocusDirection.Down)}
                        )
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                    OutlinedTextField(
                        value = state.password,
                        onValueChange = {
                            viewModel.publishIntent(AuthorizationIntent.ChangePassword(it))
                        },
                        trailingIcon = {
                            IconButton(onClick = {
                                passwordVisibility.value = !passwordVisibility.value
                            }) {

                                Icon(
                                    painter = painterResource(
                                        id = R.drawable.password_eye
                                    ),
                                    contentDescription = "Скрыть пароль",
                                    tint = if (passwordVisibility.value) primaryColor else Color.Gray
                                )

                            }
                        },
                        label = { Text(text = "Password") },
                        placeholder = { Text(text = "Password") },
                        singleLine = true,
                        visualTransformation = if (passwordVisibility.value) VisualTransformation.None
                        else PasswordVisualTransformation(),
                        modifier = Modifier
                            .height(56.dp)
                            .fillMaxWidth(0.8f),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {focusManager.clearFocus()}
                        )
                    )
                    AnimatedVisibility(visible = state.passwordAndEmailIsInvalid) {
                        Text(
                            text = "Неправильный логин или пароль... \nПопробуйте ещё раз.",
                            color = Color.Red,
                            modifier = Modifier
                                .padding(16.dp)

                        )
                    }
                    AnimatedVisibility(visible = state.passwordAndEmailIsEmpty) {
                        Text(
                            text = "Поля электронной почты или пароля пусты. Заполните их.",
                            color = Color.Red,
                            modifier = Modifier
                                .padding(16.dp)

                        )
                    }


                }
            }
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
            ) {

                Button(
                    onClick = {
                        viewModel.publishIntent(AuthorizationIntent.Login)},
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = myAuthCarColor,
                        contentColor = myAuthBackColor
                    ),

                    )
                {
                    Text(
                        text = "Войти",
                        fontSize = 20.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}