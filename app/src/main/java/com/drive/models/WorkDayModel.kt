package com.drive.models

data class WorkDayModel(
    val idWorkDay: String = "",
    val instructor: String = "",
    val car: String = "",
    val date: String = "",
    val isWorkDayEnd: Boolean = false,
    val time: List<String> = emptyList()
)
