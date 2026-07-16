package com.anmp.habittracker_malasngoding_anmp.view

import android.view.View
import com.anmp.habittracker_malasngoding_anmp.model.HabitModel

interface HabitItemListener {
    fun onPlusClick(v: View, habit: HabitModel)
    fun onMinusClick(v: View, habit: HabitModel)
}