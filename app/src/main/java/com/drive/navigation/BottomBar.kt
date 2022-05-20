package com.drive.navigation

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.drive.R
import com.drive.ui.theme.myBottomBarColor

enum class BottomItem {
    MAIN,
    HISTORY,
    INFO
}

@Composable
fun BottomBar(
    pickedItem: BottomItem,
    onClick: (BottomItem) -> Unit
) {
    BottomNavigation(
        elevation = 5.dp,
        backgroundColor = myBottomBarColor,
        modifier = Modifier.clip(RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp))
    ) {
        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_main),
                    contentDescription = null
                )
            },
            label = {
                    Text(text = "Add")
            },
            selected = pickedItem == BottomItem.MAIN,
            selectedContentColor = Color.White,
            unselectedContentColor = Color.White.copy(alpha = 0.4f),
            onClick = { onClick(BottomItem.MAIN) },
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_history),
                    contentDescription = null
                )
            },
            label = {
                Text(text = "History")
            },
            selected = pickedItem == BottomItem.HISTORY,
            selectedContentColor = Color.White,
            unselectedContentColor = Color.White.copy(alpha = 0.4f),
            onClick = { onClick(BottomItem.HISTORY) },
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = null
                )
            },
            label = {
                Text(text = "Информация")
            },
            selected = pickedItem == BottomItem.INFO,
            selectedContentColor = Color.White,
            unselectedContentColor = Color.White.copy(alpha = 0.4f),
            onClick = { onClick(BottomItem.INFO) },
        )
    }
}