package com.drive.mainPage.mvi

import com.drive.baseMVI.MVIState
import com.drive.models.LessonModel
import com.drive.models.User
import com.drive.models.WorkDayModel

data class MainState(
    val workDays: List<WorkDayModel> = emptyList(),
    val pickedLesson: LessonModel = LessonModel("", "", "","","", "", false),
    val pickedWorkDay: WorkDayModel = WorkDayModel("", "", "", "", false),
    val time: Map<String, String> = emptyMap(),
    val isBottomSheetOpen: Boolean = false,
    val lessonIsOk: Boolean = false,
    val lessonError: Boolean = false,
    val isError: Boolean = false
) : MVIState