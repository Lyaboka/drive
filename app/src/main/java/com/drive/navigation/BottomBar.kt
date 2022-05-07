package com.drive.mainPage.ui

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.drive.R

enum class BottomItem {
    MAIN,
    HISTORY
}

@Composable
fun BottomBar(
    pickedItem: BottomItem,
    onClick: (BottomItem) -> Unit
) {
    BottomNavigation(
        elevation = 5.dp
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
    }
}