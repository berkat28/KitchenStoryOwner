package com.berkat.kitchenstoryowner.repository.identitaspemilik

import com.berkat.kitchenstoryowner.model.KitchenResponse
import com.berkat.kitchenstoryowner.utils.Resource
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface IdentitasPemilikRepository {

    suspend fun sendIdentitas(
        token:String,
        phone:RequestBody,
        nik: RequestBody,
        ktp_name: RequestBody,
        ktp_picture: MultipartBody.Part
    ): Resource<KitchenResponse>
}