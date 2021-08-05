package com.berkat.kitchenstoryowner.repository.register

import com.berkat.kitchenstoryowner.model.KitchenResponse
import com.berkat.kitchenstoryowner.network.AuthApi
import com.berkat.kitchenstoryowner.utils.Resource
import javax.inject.Inject

class DefaultRegisterRepository @Inject constructor(val api: AuthApi) : RegisterRepository {


    override suspend fun register(
        name: String,
        email: String,
        password: String,
        brand_name: String,
    ): Resource<KitchenResponse> {

        return try {
            val response = api.register(name, email, password, brand_name)
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