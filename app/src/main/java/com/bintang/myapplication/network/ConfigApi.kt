package com.bintang.myapplication.network

import com.bintang.myapplication.ui.login.ResLogin
import io.reactivex.rxjava3.core.Flowable
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ConfigApi {

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") eamil:String,
        @Field("password") password:String
    ):Flowable<ResLogin>
}