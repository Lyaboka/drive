package com.drive.models

data class LessonModel(
    val idLesson: String = "",
    val idWorkDay: String = "",
    val instructor: String = "",
    val study: String = "",
    val car: String = "",
    val date: String = "",
    val isLessonEnd: Boolean = false,
    val time: String = ""
)
