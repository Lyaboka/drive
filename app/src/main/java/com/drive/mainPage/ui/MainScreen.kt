package com.drive.mainPage.ui


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.drive.DataRepository
import com.drive.R
import com.drive.models.WorkDayModel
import com.drive.mainPage.mvi.MainIntent
import com.drive.mainPage.mvi.MainState
import com.drive.mainPage.mvi.MainViewModel
import com.drive.ui.theme.myAuthCarColor
import com.drive.ui.theme.myLessonBackColor
import com.drive.ui.theme.myLessonCarColor
import com.drive.ui.theme.whiteBackground

@ExperimentalMaterialApi
@Composable
fun MainScreen(
    state: MainState,
    viewModel: MainViewModel
) {
    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    LaunchedEffect(key1 = state.isBottomSheetOpen, block = {
        if (state.isBottomSheetOpen)
            sheetState.show()
        else
            sheetState.hide()
    })
    ModalBottomSheetLayout(
        sheetBackgroundColor = Color.White,
        sheetElevation = 5.dp,
        sheetShape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
        sheetState = sheetState,
        sheetContent = {
            Box(modifier = Modifier.defaultMinSize(minHeight = 50.dp)) {
                TimePeriods(timePeriods = state.pickedWorkDay.time, onClick = {
                    viewModel.publishIntent(MainIntent.SetTimePeriod(timePeriod = it))
                    viewModel.publishIntent(MainIntent.OpenOrCloseBS)
                }
                )

            }
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            myLessonBackColor,
                            myAuthCarColor,
                            myLessonCarColor
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
                        id = R.drawable.history_im
                    ),
                    contentDescription = "Login image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
                Lessons(
                    workdays = state.workDays,
                    onClick = {
                        viewModel.publishIntent(MainIntent.OpenOrCloseBS)
                        viewModel.publishIntent(MainIntent.SetLesson(pickedWorkDay = it))
                    })
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun Lessons(workdays: List<WorkDayModel>, onClick: (WorkDayModel) -> Unit) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        items(workdays) {
            Surface(modifier = Modifier.clickable { onClick(it) }) {
                DateCard(
                    date = it.date,
                    car = it.car,
                    instr = it.instructor
                )
            }
        }
    }

}

@Composable
fun TimePeriods(timePeriods: List<String>, onClick: (String) -> Unit) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 55.dp)
    ) {
        items(timePeriods) {
            Surface(modifier = Modifier.clickable { onClick(it) }) {
                TimeCard(time = it)
            }
        }
    }
}

