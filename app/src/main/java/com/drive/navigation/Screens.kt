package com.drive.navigation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class Screen : Parcelable {

    @Parcelize
    object AuthorizationPage : Screen()

    @Parcelize
    object MainPage: Screen()

}
