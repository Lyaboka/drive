package com.drive.history.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.drive.R
import com.drive.history.mvi.HistoryState
import com.drive.history.mvi.HistoryViewModel
import com.drive.ui.theme.Teal200
import com.drive.ui.theme.myMainBackColor
import com.drive.ui.theme.myWorkDayBackColor
import com.drive.ui.theme.whiteBackground

@Composable
fun HistoryScreen(state: HistoryState, viewModel: HistoryViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        myWorkDayBackColor,
                        myMainBackColor,
                        Teal200
                    )
                )
            )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(1f)
                .fillMaxHeight(0.9f)
                .clip(RoundedCornerShape(30.dp))
                .background(whiteBackground)


        ) {
            Image(
                painter = painterResource(
                    id = R.drawable.workday_im
                ),
                contentDescription = "image",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                items(state.lessonList) {
                    LessonCard(
                        date = it.date,
                        car = it.car,
                        instr = it.instructor,
                        time = it.time,
                        isEnd = it.isLessonEnd
                    )
                }
            }
        }
    }
}