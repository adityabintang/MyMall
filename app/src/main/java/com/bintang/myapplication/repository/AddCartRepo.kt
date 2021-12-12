package com.bintang.myapplication.repository

import com.bintang.myapplication.network.ConfigNetwork
import com.bintang.myapplication.ui.detail.ResAddCart
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class AddCartRepo(var composite: CompositeDisposable) {
    private val api = ConfigNetwork.getRetrofit()
    fun addCart(
        idProduct: Int,
        idUser: Int,
        qty: Int,
        resSuccess: (ResAddCart) -> Unit,
        resError: (Throwable) -> Unit
    ) {
        composite.add(
            api.addCart(idProduct, idUser, qty)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    resSuccess(it)
                }, {
                    resError(it)
                })
        )
    }
}