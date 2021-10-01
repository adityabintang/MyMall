package com.bintang.myapplication.network

import com.bintang.myapplication.ui.home.ResHomeProduk
import com.bintang.myapplication.ui.login.ResLogin
import com.bintang.myapplication.ui.register.ResRegister
import io.reactivex.rxjava3.core.Flowable
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ConfigApi {

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") eamil: String,
        @Field("password") password: String
    ): Flowable<ResLogin>

    @FormUrlEncoded
    @POST("register")
    fun register(
        @Field("email") email: String,
        @Field("first_name") firstName: String,
        @Field("last_name") lastName: String,
        @Field("password") password: String
    ): Flowable<ResRegister>


    @GET("get-home_products")
    fun getProduct(): Flowable<ResHomeProduk>

}