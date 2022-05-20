package com.drive.navigation


import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.with
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.drive.DataRepository
import com.drive.authorizationPage.mvi.AuthorizationState
import com.drive.authorizationPage.mvi.AuthorizationViewModel
import com.drive.authorizationPage.ui.AuthorizationScreen
import com.drive.history.ui.HistoryScreen
import com.drive.history.mvi.HistoryState
import com.drive.history.mvi.HistoryViewModel
import com.drive.infoPage.InfoScreen
import com.drive.mainPage.mvi.MainState
import com.drive.mainPage.mvi.MainViewModel
import com.drive.mainPage.ui.MainScreen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dev.olshevski.navigation.reimagined.*

@OptIn(ExperimentalAnimationApi::class)
@ExperimentalMaterialApi
@Composable
fun NavHostScreen() {

    val pickedBottomItem = remember { mutableStateOf(BottomItem.MAIN) }
    val bottomBarIsOpen = remember { mutableStateOf(false) }

    val customTransitionSpec = AnimatedNavHostTransitionSpec<Any?> { action, _, _ ->
        val direction = if (action == NavAction.Pop) {
            AnimatedContentScope.SlideDirection.End
        } else {
            AnimatedContentScope.SlideDirection.Start
        }
        slideIntoContainer(direction) with slideOutOfContainer(direction)
    }
    val navController = rememberNavController(
        startDestination = if (DataRepository.firebaseUser != null) {
            Screen.SplashPage
        } else {
            Screen.AuthorizationPage
        }
    )

    NavBackHandler(navController)
    navController.onBackstackChange = {
        if (it.entries.isNotEmpty()) {
            when (it.entries.last().destination) {
                Screen.MainPage -> {
                    pickedBottomItem.value = BottomItem.MAIN
                }
                Screen.HistoryPage -> pickedBottomItem.value = BottomItem.HISTORY
                else -> {
                }
            }
        }
    }

    AnimatedNavHost(
        controller = navController,
        transitionSpec = customTransitionSpec
    ) { screen ->
        when (screen) {
            Screen.SplashPage -> {
                bottomBarIsOpen.value = false
                SplashScreen(navController)
            }
            Screen.AuthorizationPage -> {
                val viewModel =
                    remember { AuthorizationViewModel(AuthorizationState(), navController) }
                val state by viewModel.state.collectAsState()
                bottomBarIsOpen.value = false
                AuthorizationScreen(state = state, viewModel = viewModel)
            }
            Screen.MainPage -> {
                val viewModel = remember { MainViewModel(MainState(),navController) }
                val state by viewModel.state.collectAsState()
                bottomBarIsOpen.value = true

                MainScreen(state = state, viewModel = viewModel)
            }
            Screen.HistoryPage -> {
                val viewModel = remember { HistoryViewModel(HistoryState()) }
                val state by viewModel.state.collectAsState()
                HistoryScreen(state = state, viewModel = viewModel)
            }
            Screen.InfoPage -> {
                InfoScreen(navController)
            }
        }
    }
    if (bottomBarIsOpen.value) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomStart
        ) {
            BottomBar(pickedItem = pickedBottomItem.value, onClick = {
                when (it) {
                    BottomItem.MAIN -> {
                        if (pickedBottomItem.value == BottomItem.MAIN) return@BottomBar
                        pickedBottomItem.value = BottomItem.MAIN
                        navController.navigate(Screen.MainPage)
                    }
                    BottomItem.HISTORY -> {
                        if (pickedBottomItem.value == BottomItem.HISTORY) return@BottomBar
                        pickedBottomItem.value = BottomItem.HISTORY
                        navController.navigate(Screen.HistoryPage)
                    }
                    BottomItem.INFO -> {
                        if (pickedBottomItem.value == BottomItem.INFO) return@BottomBar
                        pickedBottomItem.value = BottomItem.INFO
                        navController.navigate(Screen.InfoPage)
                    }
                }
            })
        }
    }
}