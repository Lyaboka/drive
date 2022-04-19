package com.drive.mainPage.mvi

import com.drive.baseMVI.MVIViewModel
import com.drive.navigation.Screen
import dev.olshevski.navigation.reimagined.NavController

class MainViewModel(
    state: MainState,
    navigation: NavController<Screen>
) : MVIViewModel
        <MainState,
         MainIntent,
         MainNavIntent> (state, navigation) {

    override fun changeState(intent: MainIntent): MainState =
        when (intent) {
            is MainIntent.SetDate -> setDate(intent = intent, state = state.value)
        }


    override fun navigateToScreen(navIntent: MainNavIntent) {
        TODO("Not yet implemented")
    }

    override fun publishIntent(intent: MainIntent) {
       reduceIntent(intent)
    }

    private fun setDate(
        state: MainState,
        intent: MainIntent.SetDate
    ): MainState {
        return state.copy(date = intent.date)
    }

}