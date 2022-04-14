package com.drive.authorizationPage.mvi

import com.drive.baseMVI.MVIIntent

sealed class AuthorizationIntent: MVIIntent {
    class ChangeEmail(val login: String): AuthorizationIntent()
    class ChangePassword(val password: String): AuthorizationIntent()
}