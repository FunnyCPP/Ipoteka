package com.codart.ipoteka.data.remote

import com.codart.ipoteka.data.entities.LoginRequest
import com.codart.ipoteka.data.entities.LoginResponse
import com.codart.ipoteka.data.entities.TokenResponse
import com.codart.ipoteka.utils.Values
import retrofit2.Response
import retrofit2.http.*

interface AuthService {
    @POST(Values.PATH_GET_TOKEN)
    suspend fun getToken(@Header("Authorization") token: String = Values.TOKEN_BASIC): Response<TokenResponse>

    @POST(Values.PATH_LOGIN)
    suspend fun performLogin(@Header("Authorization") token: String,
                             @Body body: LoginRequest): Response<LoginResponse>
}