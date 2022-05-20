package com.drive.history.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.drive.common.robotoBold
import com.drive.common.robotoRegular
import com.drive.common.robotoThin

@Composable
fun LessonCard(date: String, car: String, instr: String, time: String, isEnd: Boolean) {
    Card(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxWidth(0.4f)
            ) {
                Text(
                    text = car,
                    fontSize = 16.sp,
                    fontFamily = robotoBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(
                        start = 8.dp,
                        top = 16.dp,
                        bottom = 8.dp,
                    )
                )
                Text(
                    text = instr,
                    fontSize = 16.sp,
                    fontFamily = robotoRegular,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(
                        start = 8.dp,
                        top = 8.dp,
                        bottom = 16.dp,
                    )
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth(0.6f)
            ) {
                Text(
                    text = date,
                    fontSize = 16.sp,
                    fontFamily = robotoRegular,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(
                        start = 16.dp,
                        top = 16.dp,
                        bottom = 8.dp,
                        end = 16.dp
                    )
                )

                Text(
                    text = time,
                    fontSize = 16.sp,
                    fontFamily = robotoRegular,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(
                        start = 16.dp,
                        top = 8.dp,
                        bottom = 16.dp,
                        end = 16.dp
                    )
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (isEnd) {
                    Icon(imageVector = Icons.Filled.Check,
                        contentDescription = "",
                        tint = Color.Green
                    )
                } else {
                    Icon(imageVector = Icons.Filled.Close,
                        contentDescription = "",
                        tint = Color.Red
                    )
                }
            }
        }
    }
}