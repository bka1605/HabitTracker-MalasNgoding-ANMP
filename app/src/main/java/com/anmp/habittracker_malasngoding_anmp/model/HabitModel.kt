package com.anmp.habittracker_malasngoding_anmp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "habit")
data class HabitModel(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var habitName: String,
    var shortDescription: String,
    var goal: Int,
    var unit: String,
    var icon: String,
    var progress: Int = 0,
    var isCompleted: Boolean = false
)