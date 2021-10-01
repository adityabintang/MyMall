package com.bintang.myapplication.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import com.bintang.myapplication.repository.Registerrepo

class RegisterViewModel() : ViewModel() {
    private var repo = Registerrepo()
    var success = MutableLiveData<ResRegister>()
    var error = MutableLiveData<Throwable>()
    fun registerUser(
        email: String,
        firstName: String,
        lastName: String,
        password: String,
    ) {
        repo.register(email, firstName, lastName, password, {
            success.value = it
        }, {
            error.value = it
        })
    }

    fun success(): LiveData<ResRegister> {
        return success
    }

    fun Error(): LiveData<Throwable> {
        return error
    }
}