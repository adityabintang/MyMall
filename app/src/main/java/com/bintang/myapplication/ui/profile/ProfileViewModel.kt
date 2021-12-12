package com.bintang.myapplication.ui.profile


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bintang.myapplication.repository.ProfileRepo
import io.reactivex.rxjava3.disposables.CompositeDisposable

class ProfileViewModel : ViewModel() {
    private var compositeDisposable: CompositeDisposable

    init {
        compositeDisposable = CompositeDisposable()
    }

    private var repo = ProfileRepo(compositeDisposable)
    val success = MutableLiveData<ResProfile>()
    val isError = MutableLiveData<Throwable>()
    private var isLoading = MutableLiveData<Boolean>()

    fun getProfile(id: Int) {
        isLoading.value = true
        repo.profileRepo(id, {
            success.value = it
            isLoading.value = false
        }, {
            isError.value = it
            isLoading.value = false
        })
    }

    fun isSuccess(): LiveData<ResProfile> {
        return success
    }

    fun isError(): LiveData<Throwable> {
        return isError
    }

    fun isLoading(): LiveData<Boolean> {
        return isLoading
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}