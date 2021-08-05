package com.berkat.kitchenstoryowner.repository.datausaha

import com.berkat.kitchenstoryowner.model.KitchenResponse
import com.berkat.kitchenstoryowner.network.KitchenApi
import com.berkat.kitchenstoryowner.utils.Resource
import javax.inject.Inject

class DefaultDataUsahaRepository @Inject constructor(private val api: KitchenApi) :
    DataUsahaRepository {
    override suspend fun checkData(token: String): Resource<KitchenResponse> {

        return try {
            val response = api.getCheckData(token)
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