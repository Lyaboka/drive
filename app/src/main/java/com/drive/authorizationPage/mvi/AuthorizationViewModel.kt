package com.drive.authorizationPage.mvi

import android.text.TextUtils
import android.util.Patterns
import com.drive.baseMVI.MVIViewModel
import com.drive.navigation.Screen
import dev.olshevski.navigation.reimagined.NavController


class AuthorizationViewModel
    (
    state: AuthorizationState,
    navigation: NavController<Screen>
) : MVIViewModel<
        AuthorizationState,
        AuthorizationIntent> (state, navigation) {

    override fun changeState(intent: AuthorizationIntent): AuthorizationState =
        when (intent) {
            is AuthorizationIntent.ChangeEmail -> changeEmail(intent, state.value, intent.login)
            is AuthorizationIntent.ChangePassword -> state.value.copy(changePassword())
        }

    private fun changeEmail(intent: AuthorizationIntent.ChangeEmail,
                    state: AuthorizationState,
                    email: String): AuthorizationState
    {
       return if (TextUtils.isEmpty(email))  state.copy(email, false)
        else if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            state.copy(email, true)
//            reduceIntent(intent)
        } else state
    }
    private fun changePassword(intent: AuthorizationIntent.ChangePassword) {
        reduceIntent(intent)
    }
}