package com.drive.authorizationPage.mvi

import com.drive.baseMVI.MVIState

data class AuthorizationState(
    val login: String = "",
    val loginIsValid: Boolean = false,
    val password: String = "",
    val passwordIsValid: Boolean = false
): MVIState