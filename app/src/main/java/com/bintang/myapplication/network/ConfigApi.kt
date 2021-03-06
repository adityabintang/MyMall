package com.bintang.myapplication.network

import com.bintang.myapplication.ui.detail.ResAddCart
import com.bintang.myapplication.ui.home.ResHomeProduk
import com.bintang.myapplication.ui.login.ResLogin
import com.bintang.myapplication.ui.profile.ResProfile
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

    @FormUrlEncoded
    @POST("get-profile")
    fun getProfile(
        @Field("user_id") id: Int
    ):Flowable<ResProfile>


    @GET("get-home-products")
    fun getProduct(): Flowable<ResHomeProduk>

    @FormUrlEncoded
    @POST("insert-cart-item")
    fun addCart(
        @Field("product_id") idProduct:Int,
        @Field("user_id") idUser:Int,
        @Field("qty") qty:Int
    ):Flowable<ResAddCart>

}