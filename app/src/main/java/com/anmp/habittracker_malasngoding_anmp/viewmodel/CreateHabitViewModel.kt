package com.anmp.habittracker_malasngoding_anmp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.anmp.habittracker_malasngoding_anmp.model.AppDatabase
import com.anmp.habittracker_malasngoding_anmp.model.HabitModel
import com.anmp.habittracker_malasngoding_anmp.model.HabitPreference
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class CreateHabitViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {
    val habitLD = MutableLiveData<HabitModel>()
    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun fetchHabit(id: Long) {
        launch {
            val db = AppDatabase.buildDatabase(getApplication())
            val habit = db.habitDao().selectHabit(id)
            habitLD.postValue(habit)
        }
    }

    fun saveHabit(habit: HabitModel) {
        launch {
            val db = AppDatabase.buildDatabase(getApplication())
            db.habitDao().insertAll(habit)
        }
    }

    fun updateHabit(habit: HabitModel) {
        launch {
            val db = AppDatabase.buildDatabase(getApplication())
            db.habitDao().updateHabit(habit)
        }
    }
}