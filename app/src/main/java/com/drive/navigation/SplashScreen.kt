package com.drive.navigation

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import com.drive.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.drive.DataRepository
import com.drive.ui.theme.myLessonCarColor
import dev.olshevski.navigation.reimagined.NavController
import dev.olshevski.navigation.reimagined.navigate
import dev.olshevski.navigation.reimagined.popAll
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navigation: NavController<Screen>) {
    var startAnim by remember { mutableStateOf(false) }
    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnim) 1f else 0f,
        animationSpec = tween(durationMillis = 2000)
    )
    LaunchedEffect(key1 = true) {
        startAnim = true
        DataRepository.getUserFromFireBase()
        delay(3000L)
        navigation.popAll()
        navigation.navigate(Screen.MainPage)
    }
    Splash(alphaAnim = alphaAnim.value)
}

@Composable
fun Splash(alphaAnim: Float) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(myLessonCarColor)
    ) {
        Image(
            painter = painterResource(id = R.drawable.history_im),
            contentDescription = "Logo",
            modifier = Modifier
                .padding(end = 20.dp)
                .fillMaxWidth()
                .alpha(alphaAnim)
        )
        Spacer(modifier = Modifier.padding(40.dp))
        Text(
            text = "Автошкола Технопарк здравствуйте!!",
            Modifier
                .fillMaxWidth()
                .alpha(alphaAnim),
            textAlign = TextAlign.Center
        )
    }
}