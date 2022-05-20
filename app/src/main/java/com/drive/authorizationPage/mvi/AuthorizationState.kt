package com.drive.authorizationPage.mvi

import com.drive.baseMVI.MVIState

data class AuthorizationState(
    val email: String = "",
    val password: String = "",
    val passwordAndEmailIsInvalid: Boolean = false,
    val passwordAndEmailIsEmpty: Boolean = false
): MVIState