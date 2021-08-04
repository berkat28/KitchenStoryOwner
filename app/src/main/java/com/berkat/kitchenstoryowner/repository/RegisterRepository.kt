package com.berkat.kitchenstoryowner.repository

import com.berkat.kitchenstoryowner.model.KitchenResponse
import com.berkat.kitchenstoryowner.utils.Resource
import okhttp3.RequestBody

interface RegisterRepository {

    suspend fun register(
        email: RequestBody,
        fullname: RequestBody,
        password: RequestBody,
        brand_name: RequestBody,
        token_notification: RequestBody,
        role: RequestBody
    ): Resource<KitchenResponse>

}