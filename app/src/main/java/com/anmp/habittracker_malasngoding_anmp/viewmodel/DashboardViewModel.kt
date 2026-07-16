package com.anmp.habittracker_malasngoding_anmp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.anmp.habittracker_malasngoding_anmp.model.AppDatabase
import com.anmp.habittracker_malasngoding_anmp.model.HabitModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DashboardViewModel(application: Application) :
    AndroidViewModel(application) {

    private val dao = AppDatabase(getApplication()).habitDao()

    val habitListLD = MutableLiveData<List<HabitModel>>()

    fun refresh() {
        viewModelScope.launch(Dispatchers.IO) {
            habitListLD.postValue(dao.getAllHabits())
        }
    }

    fun addProgress(habit: HabitModel) {
        viewModelScope.launch(Dispatchers.IO) {
            habit.progress = (habit.progress + 1).coerceAtMost(habit.goal)
            habit.isCompleted = habit.progress >= habit.goal

            dao.updateHabit(habit)

            habitListLD.postValue(dao.getAllHabits())
        }
    }

    fun minusProgress(habit: HabitModel) {
        viewModelScope.launch(Dispatchers.IO) {
            habit.progress = (habit.progress - 1).coerceAtLeast(0)
            habit.isCompleted = habit.progress >= habit.goal

            dao.updateHabit(habit)

            habitListLD.postValue(dao.getAllHabits())
        }
    }
}