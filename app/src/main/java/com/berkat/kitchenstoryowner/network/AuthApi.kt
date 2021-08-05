package com.berkat.kitchenstoryowner.network

import com.berkat.kitchenstoryowner.model.KitchenResponse
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface AuthApi {

    @FormUrlEncoded
    @POST("/login/kitchen")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<KitchenResponse>


    @FormUrlEncoded
    @POST("/register/kitchen")
    suspend fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("brand") brand: String,
    ): Response<KitchenResponse>


}