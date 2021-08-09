package com.berkat.kitchenstoryowner.repository.identitaspemilik

import com.berkat.kitchenstoryowner.model.KitchenResponse
import com.berkat.kitchenstoryowner.network.KitchenApi
import com.berkat.kitchenstoryowner.utils.Resource
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class DefaultIndentitasPemilikRepository @Inject constructor(val api:KitchenApi) :IdentitasPemilikRepository {
    override suspend fun sendIdentitas(
        token:String,
        phone:RequestBody,
        nik: RequestBody,
        ktp_name: RequestBody,
        ktp_picture: MultipartBody.Part
    ): Resource<KitchenResponse> {
        return try {
            val response = api.
            registerOwner(token,phone,nik, ktp_name, ktp_picture)
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