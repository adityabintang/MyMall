package com.bintang.myapplication.repository

import com.bintang.myapplication.network.ConfigNetwork
import com.bintang.myapplication.ui.register.ResRegister
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers


class Registerrepo {
    private val api = ConfigNetwork.getRetrofit()
    private val composite = CompositeDisposable()
    fun register(
        email: String,
        firstName:String,
        lastName:String,
        password:String,
        responseSuccess: (ResRegister) -> Unit,
        resError: (Throwable) -> Unit
    ){
        composite.add(api.register(email, firstName, lastName, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responseSuccess(it)
            }, {
                resError(it)
            })
        )
    }
}