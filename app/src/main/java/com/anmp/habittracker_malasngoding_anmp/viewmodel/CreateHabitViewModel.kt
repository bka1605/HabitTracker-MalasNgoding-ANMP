package com.anmp.habittracker_malasngoding_anmp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.anmp.habittracker_malasngoding_anmp.model.AppDatabase
import com.anmp.habittracker_malasngoding_anmp.model.HabitModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreateHabitViewModel(application: Application) :
    AndroidViewModel(application) {

    private val habitDao = AppDatabase(getApplication()).habitDao()

    fun saveHabit(habit: HabitModel) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                habitDao.insertHabit(habit)
                Log.d("CreateHabitVM", "Berhasil insert: $habit")
            } catch (e: Exception) {
                Log.d("CreateHabitVM", "Gagal insert: $habit")
            }
        }
    }
}