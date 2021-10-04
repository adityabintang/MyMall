package com.bintang.myapplication.ui.home
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import com.bintang.myapplication.repository.HomeRepo
import io.reactivex.rxjava3.disposables.CompositeDisposable

class HomeViewModel() : ViewModel() {
    private val compositeDisposable: CompositeDisposable

    init {
        compositeDisposable = CompositeDisposable()
    }
    private var repo = HomeRepo(compositeDisposable)
    var isSuccess = MutableLiveData<ResHomeProduk>()
    var isError = MutableLiveData<Throwable>()
    private var isLoading = MutableLiveData<Boolean>()

    fun getProduk(){
        isLoading.value = true
        repo.getProduk({
            isLoading.value = false
            isSuccess.value = it
        }, {
            isLoading.value = false
            isError.value = it
        })

    }

    fun isSuccess(): LiveData<ResHomeProduk>{
        return isSuccess
    }
    fun isError(): LiveData<Throwable>{
        return isError
    }
    fun isLoading(): LiveData<Boolean>{
        return  isLoading
    }
}