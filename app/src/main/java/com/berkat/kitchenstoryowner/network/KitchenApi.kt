package com.berkat.kitchenstoryowner.network

import com.berkat.kitchenstoryowner.model.KitchenResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface KitchenApi {


    @GET("/kitchen/bank")
    @Headers("Accept: application/json")
    suspend fun getCheckData(
        @Header("Authorization") token: String
    ): Response<KitchenResponse>

    @POST("/kitchen/bank")
    @FormUrlEncoded
    suspend fun sendBankData(
        @Header("Authorization") token: String,
        @Field("bank_name") bank_name: String,
        @Field("account_number") account_number: String,
        @Field("account_name") account_name: String
    ):Response<KitchenResponse>

    @Multipart
    @POST("/register/owner")
    suspend fun registerOwner(
        @Header("Authorization")token:String,
        @Part("phone")phone:RequestBody,
        @Part("nik") nik: RequestBody,
        @Part("ktp_name") ktp_name: RequestBody,
        @Part ktp_picture: MultipartBody.Part,
    ):Response<KitchenResponse>


}