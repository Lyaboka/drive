package com.drive.authorizationPage.mvi

import com.drive.DataRepository
import com.drive.baseMVI.MVIViewModel
import com.drive.navigation.Screen
import dev.olshevski.navigation.reimagined.NavController
import dev.olshevski.navigation.reimagined.navigate
import dev.olshevski.navigation.reimagined.popAll

class AuthorizationViewModel
    (
    state: AuthorizationState,
    private val navigation: NavController<Screen>
) : MVIViewModel<
        AuthorizationState,
        AuthorizationIntent,
        AuthorizationNavIntent>(state) {

    init {
        DataRepository.getNewInstance()
    }

    override fun changeState(intent: AuthorizationIntent): AuthorizationState =
        when (intent) {
            is AuthorizationIntent.ChangeEmail -> changeEmail(intent = intent, state = state.value)
            is AuthorizationIntent.ChangePassword -> changePassword(
                intent = intent,
                state = state.value
            )
            AuthorizationIntent.Login -> login(state = state.value)
            AuthorizationIntent.EnterInvalidLogin -> state.value.copy(
                passwordAndEmailIsInvalid = if (!state.value.passwordAndEmailIsInvalid)
                    true
                else
                    state.value.passwordAndEmailIsInvalid
            )
        }

    override fun navigateToScreen(navIntent: AuthorizationNavIntent) =
        when (navIntent) {
            AuthorizationNavIntent.GoToSplashScreen -> navigate(Screen.SplashPage)
        }

    override fun publishIntent(intent: AuthorizationIntent) {
        reduceIntent(intent)
    }

    override fun navigate(screen: Screen) {
        navigation.popAll()
        navigation.navigate(screen)
    }

    private fun changeEmail(
        intent: AuthorizationIntent.ChangeEmail,
        state: AuthorizationState
    ): AuthorizationState {
        return if (intent.email == state.email) {
            state
        } else {
            state.copy(email = intent.email)
        }
    }

    private fun login(state: AuthorizationState): AuthorizationState {
        return if (state.email == "" || state.password == "") {
            state.copy(passwordAndEmailIsEmpty = true, passwordAndEmailIsInvalid = false)
        } else {
            DataRepository.auth.signInWithEmailAndPassword(state.email, state.password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        navigateToScreen(AuthorizationNavIntent.GoToSplashScreen)
                    } else {
                        reduceIntent(AuthorizationIntent.EnterInvalidLogin)
                    }
                }
            state.copy(passwordAndEmailIsEmpty = false)
        }
    }

    private fun changePassword(
        intent: AuthorizationIntent.ChangePassword,
        state: AuthorizationState
    ): AuthorizationState {
        return if (intent.password == state.password) {
            state
        } else {
            state.copy(password = intent.password)
        }
    }
}