package com.drive.mainPage.mvi

import com.drive.DataRepository
import com.drive.baseMVI.MVIViewModel
import com.drive.models.LessonModel
import com.drive.navigation.Screen
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import dev.olshevski.navigation.reimagined.NavController
import dev.olshevski.navigation.reimagined.navigate

class MainViewModel(
    state: MainState,
    val navigation: NavController<Screen>
) : MVIViewModel
<MainState,
        MainIntent,
        MainNavIntent>(state) {

    init {
        Firebase.firestore
            .collection("data").document("usersData")
            .collection("workDays")
            .whereEqualTo("car", DataRepository.user.car)
            .get()
            .addOnSuccessListener {
                reduceIntent(MainIntent.GetWorkDays(workDays = it.toObjects()))
            }

    }

    override fun changeState(intent: MainIntent): MainState =
        when (intent) {
            is MainIntent.GetWorkDays -> state.value.copy(workDays = intent.workDays)
            is MainIntent.SetTimePeriod -> setLesson(intent = intent, state = state.value)
            is MainIntent.SetLesson -> state.value.copy(pickedWorkDay = intent.pickedWorkDay)
            MainIntent.OpenOrCloseBS -> openOrCloseBS(state = state.value)
        }

    private fun setLesson(intent: MainIntent.SetTimePeriod, state: MainState): MainState {

        val lessonId = Firebase.firestore
            .collection("data").document("usersData")
            .collection("lessons")
            .document()
            .id
        val lesson = LessonModel(
            idLesson = lessonId,
            idWorkDay = state.pickedWorkDay.idWorkDay,
            instructor = state.pickedWorkDay.instructor,
            study = DataRepository.user.fio,
            car = state.pickedWorkDay.car,
            date = state.pickedWorkDay.date,
            isLessonEnd = false,
            time = intent.timePeriod
        )

        val updatedTime = arrayListOf<String>()
        state.pickedWorkDay.time.filterTo(updatedTime) { time -> intent.timePeriod != time }
        if (updatedTime.size == 0) {
            Firebase.firestore
                .collection("data").document("usersData")
                .collection("workDays")
                .document(state.pickedWorkDay.idWorkDay)
                .delete()
        } else {
            Firebase.firestore
                .collection("data").document("usersData")
                .collection("workDays")
                .document(state.pickedWorkDay.idWorkDay)
                .update("time", updatedTime)
        }

        Firebase.firestore
            .collection("data").document("usersData")
            .collection("lessons")
            .document(lessonId)
            .set(lesson)
        navigateToScreen(MainNavIntent.GoToWorkDaysScreen)
        return state.copy(pickedLesson = lesson)
    }


    override fun navigateToScreen(navIntent: MainNavIntent) =
        when (navIntent) {
            MainNavIntent.GoToWorkDaysScreen -> navigate(Screen.HistoryPage)
        }

    override fun publishIntent(intent: MainIntent) {
        reduceIntent(intent)
    }

    override fun navigate(screen: Screen) {
        navigation.navigate(screen)
    }

    private fun openOrCloseBS(state: MainState): MainState {
        return if (state.isBottomSheetOpen) {
            state.copy(isBottomSheetOpen = false)
        } else {
            state.copy(isBottomSheetOpen = true)
        }
    }


}
