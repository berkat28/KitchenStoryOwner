package com.berkat.kitchenstoryowner.repository.informasirekening

import com.berkat.kitchenstoryowner.model.KitchenResponse
import com.berkat.kitchenstoryowner.network.KitchenApi
import com.berkat.kitchenstoryowner.utils.Resource
import javax.inject.Inject

class DefaultInformasiRekeningRepository @Inject constructor(val api:KitchenApi) :InformasiRekeningRepository {

    override suspend fun sendBankData(
        token:String,
        bank_name: String,
        account_number: String,
        account_name: String
    ): Resource<KitchenResponse> {
        return try {
            val response = api.sendBankData(token,bank_name,account_number, account_name)
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