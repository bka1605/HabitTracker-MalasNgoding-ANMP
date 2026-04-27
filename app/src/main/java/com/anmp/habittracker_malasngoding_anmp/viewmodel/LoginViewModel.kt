package com.anmp.habittracker_malasngoding_anmp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {
    val loginStatus = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()

    fun Login(username: String, pass: String) {
        if(username=="student" && pass=="123") {
            loginStatus.value = true
        } else {
            loginStatus.value = false
            errorMessage.value = "Username atau Password salah!"
        }
    }
}