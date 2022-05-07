package com.drive.history

import com.drive.baseMVI.MVIViewModel
import com.drive.navigation.Screen
import dev.olshevski.navigation.reimagined.NavController

class HistoryViewModel(
    state: HistoryState,
    navigation: NavController<Screen>
) : MVIViewModel<HistoryState, HistoryIntent, HistoryNavIntent>(
    state
) {


    override fun publishIntent(intent: HistoryIntent) {
        TODO("Not yet implemented")
    }

    override fun changeState(intent: HistoryIntent): HistoryState {
        TODO("Not yet implemented")
    }

    override fun navigateToScreen(navIntent: HistoryNavIntent) {
        TODO("Not yet implemented")
    }

    override fun navigate(screen: Screen) {
        TODO("Not yet implemented")
    }
}