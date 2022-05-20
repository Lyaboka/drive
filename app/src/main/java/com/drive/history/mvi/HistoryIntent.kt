package com.drive.history.mvi

import com.drive.baseMVI.MVIIntent
import com.drive.models.LessonModel

sealed class HistoryIntent : MVIIntent {
    class GetLessons(val lessons: List<LessonModel>): HistoryIntent()

}