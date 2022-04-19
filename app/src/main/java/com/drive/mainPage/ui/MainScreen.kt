package com.drive.mainPage.ui

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.drive.mainPage.mvi.MainIntent
import com.drive.mainPage.mvi.MainState
import com.drive.mainPage.mvi.MainViewModel

import java.util.*


@Composable
fun MainScreen(
    context: Context,
    state: MainState,
    viewModel: MainViewModel
) {
    val year: Int
    val month: Int
    val day: Int

    val calendar = Calendar.getInstance()

    year = calendar.get(Calendar.YEAR)
    month = calendar.get(Calendar.MONTH)
    day = calendar.get(Calendar.DAY_OF_MONTH)

    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, _year: Int, _month: Int, _day: Int ->
            val date = "$_day/$_month/$_year"
            viewModel.publishIntent(MainIntent.SetDate(date = date))
        }, year, month, day
    )
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .wrapContentSize(Alignment.TopStart)
                .border(0.5.dp, MaterialTheme.colors.onSurface.copy(alpha = 0.5f))
                .clickable {
                    datePickerDialog.show()
                })
        {
            Row(horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Выберите дату",
                    modifier = Modifier
                        .padding(top = 10.dp, bottom = 10.dp, start = 16.dp, end = 16.dp)


                )
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(top = 10.dp, bottom = 10.dp, start = 16.dp, end = 16.dp)
                        .size(20.dp, 20.dp),
                    tint = MaterialTheme.colors.onSurface
                )
            }
        }
        Spacer(modifier = Modifier.padding(16.dp))
        Text(
            text = "Выбранная дата: ${state.date}",
            modifier = Modifier.border(0.5.dp, MaterialTheme.colors.onSurface.copy(alpha = 0.5f))
        )
    }
}