package com.anmp.habittracker_malasngoding_anmp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.anmp.habittracker_malasngoding_anmp.model.HabitModel
import com.anmp.habittracker_malasngoding_anmp.model.HabitPreference

class CreateHabitViewModel(application: Application) : AndroidViewModel(application) {

    fun saveHabit(habit: HabitModel) {
        val pref = HabitPreference(getApplication())
        pref.saveHabit(habit)
    }
}