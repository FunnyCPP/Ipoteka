package com.codart.ipoteka.data.remote

import com.codart.ipoteka.data.entities.FirebaseTokenRequest
import com.codart.ipoteka.data.entities.FirebaseTokenResponse
import com.codart.ipoteka.utils.Values
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface FirebaseTokenService {
    @POST(Values.PATH_FIREBASE_TOKEN)
    suspend fun sendFirebaseToken(@Header("Authorization") token: String,
                             @Body body: FirebaseTokenRequest
    ): Response<FirebaseTokenResponse>
}