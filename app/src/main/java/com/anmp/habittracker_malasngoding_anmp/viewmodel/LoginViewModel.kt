package com.anmp.habittracker_malasngoding_anmp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anmp.habittracker_malasngoding_anmp.model.AppDatabase
import com.anmp.habittracker_malasngoding_anmp.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class LoginViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {

    //#region login project uts
//    val loginStatus = MutableLiveData<Boolean>()
//    val errorMessage = MutableLiveData<String>()
//
//    fun Login(username: String, pass: String) {
//        if(username=="student" && pass=="123") {
//            loginStatus.value = true
//        } else {
//            loginStatus.value = false
//            errorMessage.value = "Username atau Password salah!"
//        }
//    }
    //#endregion

    //#region login project uas
    val loginResultLD = MutableLiveData<Boolean>()
    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun Login(username: String, password: String) {
        launch {
            val db = AppDatabase.buildDatabase(getApplication())
            val user = db.userDao().login(username, password)
            loginResultLD.postValue(user != null)
        }
    }

    fun seedDummyUser() {
        launch {
            val db = AppDatabase.buildDatabase(getApplication())
            db.userDao().insertAll(User(username = "student", password = "123"))
        }
    }
    //#endregion
}