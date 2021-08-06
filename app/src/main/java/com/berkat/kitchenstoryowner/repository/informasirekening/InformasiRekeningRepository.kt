package com.berkat.kitchenstoryowner.repository.informasirekening

import com.berkat.kitchenstoryowner.model.KitchenResponse
import com.berkat.kitchenstoryowner.utils.Resource

interface InformasiRekeningRepository {

    suspend fun sendBankData(
        token:String,
        bank_name: String,
        account_number: String,
        account_name: String
    ): Resource<KitchenResponse>
}