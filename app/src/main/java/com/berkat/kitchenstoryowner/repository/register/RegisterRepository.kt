package com.berkat.kitchenstoryowner.repository.register

import com.berkat.kitchenstoryowner.model.KitchenResponse
import com.berkat.kitchenstoryowner.utils.Resource
import okhttp3.RequestBody

interface RegisterRepository {

    suspend fun register(
        name: String,
        email: String,
        password: String,
        brand_name: String,
    ): Resource<KitchenResponse>

}