package com.bintang.myapplication.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bintang.myapplication.repository.LoginRepo

class LoginViewModel() : ViewModel() {
    private var repo = LoginRepo()
    val isSuccess = MutableLiveData<ResLogin>()
    val isError = MutableLiveData<Throwable>()

    fun loginUser(email: String, password: String) {
        repo.loginUser(email, password, {
            isSuccess.value = it
        }, {
            isError.value = it
        })
    }
    fun isSuccess():LiveData<ResLogin>{
        return isSuccess
    }
    fun isError():LiveData<Throwable>{
        return isError
    }
}