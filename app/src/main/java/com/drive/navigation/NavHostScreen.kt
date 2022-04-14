package com.drive.navigation


import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.drive.authorizationPage.mvi.AuthorizationState
import com.drive.authorizationPage.mvi.AuthorizationViewModel
import com.drive.authorizationPage.ui.AuthorizationScreen
import dev.olshevski.navigation.reimagined.NavBackHandler
import dev.olshevski.navigation.reimagined.NavHost
import dev.olshevski.navigation.reimagined.rememberNavController

@Composable
fun NavHostScreen() {
    val navController = rememberNavController<Screen>(
        startDestination = Screen.AuthorizationPage
    )
    NavBackHandler(navController)

    NavHost(navController) {screen ->
        when(screen) {
            Screen.AuthorizationPage -> {
                val viewModel = remember {AuthorizationViewModel(AuthorizationState(),navController) }
                val state by viewModel.state.collectAsState()
                AuthorizationScreen(state = state, viewModel = viewModel)
            }
        }
    }
}