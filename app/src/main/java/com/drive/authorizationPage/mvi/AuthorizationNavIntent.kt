package com.drive.authorizationPage.mvi

import com.drive.baseMVI.MVINavigationIntent

sealed class AuthorizationNavIntent : MVINavigationIntent {
    object GoToMainScreen : AuthorizationNavIntent()
}