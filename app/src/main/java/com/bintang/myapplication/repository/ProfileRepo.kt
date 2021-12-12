package com.bintang.myapplication.repository

import com.bintang.myapplication.network.ConfigNetwork
import com.bintang.myapplication.ui.profile.ResProfile
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers



class ProfileRepo(var composite: CompositeDisposable) {
    private val api = ConfigNetwork.getRetrofit()

    fun profileRepo(
        id:Int,
        responseSuccess: (ResProfile) -> Unit,
        resError: (Throwable) -> Unit
    ){
        composite.add(api.getProfile(id)
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