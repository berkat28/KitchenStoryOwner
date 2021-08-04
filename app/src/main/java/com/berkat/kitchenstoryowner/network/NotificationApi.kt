package com.berkat.kitchenstoryowner.network


import com.berkat.kitchenstoryowner.model.PushNotification
import com.berkat.kitchenstoryowner.utils.Constants.Companion.CONTENT_TYPE
import com.berkat.kitchenstoryowner.utils.Constants.Companion.SERVER_KEY
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface NotificationApi {

    @Headers("Authorization: key=$SERVER_KEY", "Content-Type:$CONTENT_TYPE")
    @POST("fcm/send")
    suspend fun pushNotification(
        @Body notifcation: PushNotification
    ): Response<ResponseBody>
}