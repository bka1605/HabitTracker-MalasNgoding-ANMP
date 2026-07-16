package com.anmp.habittracker_malasngoding_anmp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.anmp.habittracker_malasngoding_anmp.model.AppDatabase
import com.anmp.habittracker_malasngoding_anmp.model.HabitModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreateHabitViewModel(application: Application) :
    AndroidViewModel(application) {

    private val habitDao = AppDatabase(getApplication()).habitDao()

    val habitLD = MutableLiveData<HabitModel>()

    fun fetchHabit(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            val habit = habitDao.getHabit(id)
            habit?.let {
                habitLD.postValue(it)
            }
        }
    }

    fun saveHabit(habit: HabitModel) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                habitDao.insertHabit(habit)
                Log.d("CreateHabitVM", "Berhasil insert: $habit")
            } catch (e: Exception) {
                Log.e("CreateHabitVM", "Gagal insert: ${e.message}")
            }
        }
    }

    fun updateHabit(habit: HabitModel) {
        viewModelScope.launch(Dispatchers.IO) {
            habitDao.updateHabit(habit)
        }
    }
}