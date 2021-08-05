package com.berkat.kitchenstoryowner.network

import com.berkat.kitchenstoryowner.model.KitchenResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface KitchenApi {


    @GET("/kitchen/bank")
    @Headers("Accept: application/json")
    suspend fun getCheckData(
        @Header("Authorization") token: String
    ): Response<KitchenResponse>
}