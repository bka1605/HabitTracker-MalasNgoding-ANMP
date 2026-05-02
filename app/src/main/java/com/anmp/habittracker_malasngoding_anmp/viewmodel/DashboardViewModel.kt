package com.anmp.habittracker_malasngoding_anmp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.anmp.habittracker_malasngoding_anmp.model.HabitModel
import com.anmp.habittracker_malasngoding_anmp.model.HabitPreference

class DashboardViewModel (application: Application) : AndroidViewModel(application){
    private val pref = HabitPreference(getApplication())

    fun getHabits(): List<HabitModel> = pref.getHabits()

    fun addProgress(habit: HabitModel) {
        val next = (habit.progress + 1).coerceAtMost(habit.goal)
        pref.updateHabitProgress(habit.id, next)
    }

    fun minusProgress(habit: HabitModel) {
        val next = (habit.progress - 1).coerceAtLeast(0)
        pref.updateHabitProgress(habit.id, next)
    }
}