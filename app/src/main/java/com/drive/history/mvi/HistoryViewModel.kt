package com.drive.history.mvi

import android.util.Log
import com.drive.DataRepository
import com.drive.baseMVI.MVIViewModel
import com.drive.models.LessonModel
import com.drive.navigation.Screen
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase

class HistoryViewModel(
    state: HistoryState,
) : MVIViewModel
        <HistoryState,
        HistoryIntent,
        HistoryNavIntent>(
    state
) {
    init {
        Firebase.firestore
            .collection("data").document("usersData")
            .collection("lessons")
            .whereEqualTo("study", DataRepository.user.fio)
            .get()
            .addOnSuccessListener {
                reduceIntent(HistoryIntent.GetLessons(lessons = it.toObjects()))

            }
    }



    override fun publishIntent(intent: HistoryIntent) {
        publishIntent(intent)
    }

    override fun changeState(intent: HistoryIntent): HistoryState =
        when (intent) {
            is HistoryIntent.GetLessons -> state.value.copy(lessonList = intent.lessons)
        }

    override fun navigateToScreen(navIntent: HistoryNavIntent) {
        TODO("Not yet implemented")
    }

    override fun navigate(screen: Screen) {
        TODO("Not yet implemented")
    }
}