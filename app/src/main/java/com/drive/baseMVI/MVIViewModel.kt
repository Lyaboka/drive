package com.drive.baseMVI

import androidx.lifecycle.ViewModel
import com.drive.navigation.Screen
import dev.olshevski.navigation.reimagined.NavController
import dev.olshevski.navigation.reimagined.navigate
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class MVIViewModel<S: MVIState, I: MVIIntent, N:MVINavigationIntent>
   (
    state: S
) : ViewModel()  {
        private val _state = MutableStateFlow(state)
        val state = _state.asStateFlow()


    fun reduceIntent(intent: I) {
        _state.value = changeState(intent)
    }

    abstract fun navigate(screen: Screen)

    abstract fun navigateToScreen(navIntent: N)

    abstract fun publishIntent(intent: I)

    protected abstract fun changeState(intent: I) : S
}