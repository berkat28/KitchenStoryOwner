package com.berkat.kitchenstoryowner.repository.datausaha

import com.berkat.kitchenstoryowner.model.KitchenResponse
import com.berkat.kitchenstoryowner.utils.Resource
import retrofit2.Response

interface DataUsahaRepository {

    suspend fun checkData(token:String):Resource<KitchenResponse>
}