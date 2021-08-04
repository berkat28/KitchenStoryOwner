package com.berkat.kitchenstoryowner.repository

import com.berkat.kitchenstoryowner.model.KitchenResponse
import com.berkat.kitchenstoryowner.network.AuthApi
import com.berkat.kitchenstoryowner.utils.Resource
import okhttp3.RequestBody
import javax.inject.Inject

class DefaultRegisterRepository @Inject constructor(val api: AuthApi) :RegisterRepository {


    override suspend fun register(
        email: RequestBody,
        fullname: RequestBody,
        password: RequestBody,
        brand_name: RequestBody,
        token_notification: RequestBody,
        role: RequestBody
    ): Resource<KitchenResponse> {

        return try {
            val response = api.register(fullname, email, password, brand_name, token_notification, role)
            val result = response.body()

            if (response.isSuccessful && result != null) {
                Resource.Success(result)
            } else {
                Resource.Error(response.message())
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error Occured")
        }
    }
}