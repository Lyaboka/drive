package com.drive.authorizationPage.mvi

import com.drive.baseMVI.MVIViewModel
import com.drive.navigation.Screen
import dev.olshevski.navigation.reimagined.NavController

class AuthorizationViewModel
    (
    state: AuthorizationState,
    navigation: NavController<Screen>
) : MVIViewModel<
        AuthorizationState,
        AuthorizationIntent,
        AuthorizationNavIntent>(state, navigation) {

    override fun changeState(intent: AuthorizationIntent): AuthorizationState =
        when (intent) {
            is AuthorizationIntent.ChangeEmail -> changeEmail(intent = intent, state = state.value)
            is AuthorizationIntent.ChangePassword -> changePassword(intent = intent, state = state.value)
        }

    override fun navigateToScreen(navIntent: AuthorizationNavIntent) =
        when (navIntent) {
            AuthorizationNavIntent.GoToMainScreen -> navigate(Screen.MainPage)
        }

    override fun publishIntent(intent: AuthorizationIntent) {
        reduceIntent(intent)
    }

    private fun changeEmail(
        intent: AuthorizationIntent.ChangeEmail,
        state: AuthorizationState
    ): AuthorizationState {
        return if (intent.email == state.email) {
            state
        } else {
            state.copy(email = intent.email, loginIsValid = true)
        }
    }

    private fun changePassword(
        intent: AuthorizationIntent.ChangePassword,
    state: AuthorizationState
    ): AuthorizationState {
        return if (intent.password == state.password) {
            state
        } else {
            state.copy(password = intent.password, passwordIsValid = true)
        }
    }


}