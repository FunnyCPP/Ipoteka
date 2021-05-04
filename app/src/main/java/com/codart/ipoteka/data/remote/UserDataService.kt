package com.codart.ipoteka.data.remote


import com.codart.ipoteka.data.entities.UserDataResponse
import com.codart.ipoteka.utils.Values
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
interface UserDataService {

    @GET(Values.PATH_USER_DATA)
    suspend fun getUserData(@Header("Authorization") token: String): Response<UserDataResponse>
}