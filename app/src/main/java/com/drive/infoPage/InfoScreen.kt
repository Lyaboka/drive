package com.drive.infoPage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.drive.DataRepository
import com.drive.R
import com.drive.common.robotoBold
import com.drive.common.robotoRegular
import com.drive.models.User
import com.drive.navigation.Screen
import com.drive.ui.theme.*
import dev.olshevski.navigation.reimagined.NavController
import dev.olshevski.navigation.reimagined.navigate

@Composable
fun InfoScreen(navigation: NavController<Screen>) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        primaryColor,
                        myWorkDayBackColor,
                        myMainBackColor,

                        )
                )
            )
    ) {
        Column {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(1f)
                    .fillMaxHeight(0.8f)
                    .clip(RoundedCornerShape(30.dp))
                    .background(whiteBackground)


            ) {
                Image(
                    painter = painterResource(
                        id = R.drawable.login_image
                    ),
                    contentDescription = "Login image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
                Card(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = "ФИО ученика",
                            textAlign = TextAlign.Center,
                            color = Color.White,
                            fontSize = 16.sp,
                            fontFamily = robotoBold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(myMainBackColor)
                                .padding(8.dp)

                        )
                        Text(
                            text = DataRepository.user.fio,
                            fontSize = 16.sp,
                            fontFamily = robotoRegular,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.padding(8.dp)
                        )
                    }

                }
                Card(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = "Машина для вождения",
                            textAlign = TextAlign.Center,
                            fontSize = 16.sp,
                            color = Color.White,
                            fontFamily = robotoBold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(myMainBackColor)
                                .padding(8.dp)

                        )
                        Text(
                            text = DataRepository.user.car,
                            fontSize = 16.sp,
                            fontFamily = robotoRegular,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }

            }
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.7f)
                    .padding(16.dp),
            ) {
                Column {
                    Button(
                        onClick = {
                            DataRepository.auth.signOut()
                            DataRepository.firebaseUser?.reload()
                            DataRepository.user = User()
                            navigation.navigate(Screen.AuthorizationPage)
                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = myMainCarColor,
                            contentColor = Color.White
                        )
                    ) {
                        Text(
                            text = "Выйти",
                            fontSize = 16.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }
    }
}