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


    @Multipart
    @POST("register")
    suspend fun register(
        @Part("email") email: RequestBody,
        @Part("name") name: RequestBody,
        @Part("password") password: RequestBody,
        @Part("brand_name") brand_name: RequestBody,
        @Part("token_notification") token_notification: RequestBody,
        @Part("role") role: RequestBody,
    ): Response<KitchenResponse>
}