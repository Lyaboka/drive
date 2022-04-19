package com.drive.navigation


import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.drive.authorizationPage.mvi.AuthorizationState
import com.drive.authorizationPage.mvi.AuthorizationViewModel
import com.drive.authorizationPage.ui.AuthorizationScreen
import com.drive.mainPage.mvi.MainState
import com.drive.mainPage.mvi.MainViewModel
import com.drive.mainPage.ui.MainScreen
import dev.olshevski.navigation.reimagined.NavBackHandler
import dev.olshevski.navigation.reimagined.NavHost
import dev.olshevski.navigation.reimagined.rememberNavController

@Composable
fun NavHostScreen() {
    val activity = LocalContext.current as ComponentActivity
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
            Screen.MainPage -> {
                val viewModel = remember { MainViewModel(MainState(),navController) }
                val state by viewModel.state.collectAsState()
                MainScreen(state = state, viewModel = viewModel, context = activity)
            }
        }
    }
}