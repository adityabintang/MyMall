package com.bintang.myapplication.repository

import com.bintang.myapplication.network.ConfigNetwork
import com.bintang.myapplication.ui.home.ResHomeProduk
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers


class HomeRepo(var composite: CompositeDisposable) {
    private val api = ConfigNetwork.getRetrofit()
    fun getProduk(
        resSuccess: (ResHomeProduk) -> Unit,
        resError: (Throwable) -> Unit
    ) {
        composite.add(
            api.getProduct().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        resSuccess(it)
                    },
                    {
                        resError(it)
                    },
                ),
        )
    }
}