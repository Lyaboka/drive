package com.drive.history

import com.drive.baseMVI.MVIState

data class HistoryState(
    val lessonList: List<Entries>,
    val isError: Boolean = false,
    val isLessonEnd: Boolean = false
) : MVIState