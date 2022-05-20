package com.drive.history.mvi

import com.drive.baseMVI.MVIState
import com.drive.models.LessonModel

data class HistoryState(
    val lessonList: List<LessonModel> = emptyList(),
    val isError: Boolean = false,
    val isLessonEnd: Boolean = false
) : MVIState