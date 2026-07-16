package com.anmp.habittracker_malasngoding_anmp.view

import com.anmp.habittracker_malasngoding_anmp.model.HabitModel

interface DashboardListener {
    fun onPlus(habit: HabitModel)
    fun onMinus(habit: HabitModel)
    fun onHabitClick(habit: HabitModel)
}