package com.anmp.habittracker_malasngoding_anmp.model

data class HabitModel(
    val id: Int = 0,
    val habitName: String,
    val shortDescription: String,
    val goal: Int,
    val unit: String,
    val icon: String,
    val progress: Int = 0,
    val isCompleted: Boolean = false
)