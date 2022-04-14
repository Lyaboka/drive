package com.drive.baseMVI

import androidx.lifecycle.ViewModel
import com.drive.navigation.Screen
import dev.olshevski.navigation.reimagined.NavController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class MVIViewModel<S: MVIState, I: MVIIntent>
   (
    state: S,
    navigation: NavController<Screen>
) : ViewModel()  {
        private val _state = MutableStateFlow(state)
        val state = _state.asStateFlow()


    fun reduceIntent(intent: I) {
        _state.value = changeState(intent)
    }

    abstract fun changeState(intent: I) : S
}