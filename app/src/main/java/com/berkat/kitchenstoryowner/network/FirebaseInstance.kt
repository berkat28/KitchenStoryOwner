package com.berkat.kitchenstoryowner.network

import com.berkat.kitchenstoryowner.utils.Constants.Companion.FIREBASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FirebaseInstance {

    companion object {
        private val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(FIREBASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        val api by lazy {
            retrofit.create(NotificationApi::class.java)

        }
    }
}