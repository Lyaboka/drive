package com.drive.mainPage.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.drive.mainPage.mvi.MainState
import com.drive.mainPage.mvi.MainViewModel
import com.drive.ui.theme.whiteBackground

@Composable
fun RecordItem(
    state: MainState,
    viewModel: MainViewModel
) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(150.dp)
        .padding(16.dp)
        .clip(RoundedCornerShape(15.dp))
        .background(whiteBackground)) {

    }
    Box(){
        Row() {
            Column() {
                Text(text = "Инструктор")
                Text(text = "Тут должен быть state.instructor")


            }

        }

    }
}