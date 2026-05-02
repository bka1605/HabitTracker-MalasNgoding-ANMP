package com.anmp.habittracker_malasngoding_anmp.model

data class HabitModel(
    val id: Long = 0L,
    val habitName: String,
    val shortDescription: String,
    val goal: Int,
    val unit: String,
    val icon: String,
    var progress: Int = 0,
    var isCompleted: Boolean = false
)