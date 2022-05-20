package com.drive.history.models

data class Entries(
    val idStudy: String = "",
    val instructor: String = "",
    val car: String = "",
    val date: String = "",
    val time: String = "",
    val isLessonEnd: Boolean = false
)
