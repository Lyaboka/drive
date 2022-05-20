package com.drive.mainPage.mvi

import com.drive.baseMVI.MVIIntent
import com.drive.models.WorkDayModel

sealed class MainIntent : MVIIntent {
    class GetWorkDays(val workDays: List<WorkDayModel>) : MainIntent()
    class SetLesson(val pickedWorkDay: WorkDayModel) : MainIntent()
    class SetTimePeriod(val timePeriod: String) : MainIntent()
    object OpenOrCloseBS : MainIntent()

}