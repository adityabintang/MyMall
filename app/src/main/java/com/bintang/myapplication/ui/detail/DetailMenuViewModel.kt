package com.bintang.myapplication.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import io.reactivex.rxjava3.disposables.CompositeDisposable
import androidx.lifecycle.MutableLiveData
import com.bintang.myapplication.repository.AddCartRepo

class DetailMenuViewModel() : ViewModel() {
    private val compositeDisposable: CompositeDisposable

    init {
        compositeDisposable = CompositeDisposable()
    }

    private var repo = AddCartRepo(compositeDisposable)
    var isSuccess = MutableLiveData<ResAddCart>()
    var isError = MutableLiveData<Throwable>()
    fun addCart(idProduct: Int, idUser: Int, qty: Int) {
        repo.addCart(idProduct, idUser, qty, {
            isSuccess.value = it
        }, {
            isError.value = it
        })
    }

    fun addSuccess(): LiveData<ResAddCart> {
        return isSuccess
    }

    fun addError(): LiveData<Throwable> {
        return isError
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}