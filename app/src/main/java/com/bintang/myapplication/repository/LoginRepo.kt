package com.bintang.myapplication.repository

import com.bintang.myapplication.network.ConfigNetwork
import com.bintang.myapplication.ui.login.ResLogin
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class LoginRepo() {
    private val api = ConfigNetwork.getRetrofit()
    private val composite = CompositeDisposable()
    fun loginUser(
        email: String,
        password: String,
        resSuccess: (ResLogin) -> Unit,
        resError: (Throwable) -> Unit
    ) {
        composite.add(
            api.login(email, password).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    resSuccess(it)
                }, {
                    resError(it)
                })
        )
    }
}