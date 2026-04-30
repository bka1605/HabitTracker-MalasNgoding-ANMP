package com.anmp.habittracker_malasngoding_anmp.model

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import androidx.core.content.edit

class HabitPreference(context: Context) {
    private val sharedPref = context.getSharedPreferences("habit_pref", Context.MODE_PRIVATE)
    private val gson = Gson()
    companion object {
        const val HABIT_KEY = "habit_key"
    }

    fun saveHabit(habit: HabitModel) {
        val currentList = getHabits().toMutableList()
        currentList.add(habit)
        val json = gson.toJson(currentList)
        sharedPref.edit {
            putString(HABIT_KEY, json)
        }
    }

    fun getHabits(): List<HabitModel> {
        val json = sharedPref.getString(HABIT_KEY, null) ?: return emptyList()
        val type = object : TypeToken<List<HabitModel>>() {}.type
        return gson.fromJson(json, type)
    }
}