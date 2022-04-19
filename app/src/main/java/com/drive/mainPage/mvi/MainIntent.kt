package com.drive.mainPage.mvi

import com.drive.baseMVI.MVIIntent

sealed class MainIntent : MVIIntent {
    class SetDate(val date: String) : MainIntent()

}