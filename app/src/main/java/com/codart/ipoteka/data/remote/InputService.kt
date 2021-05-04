package com.codart.ipoteka.data.remote

import com.codart.ipoteka.data.entities.*
import com.codart.ipoteka.utils.Values
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface InputService {
    @POST(Values.PATH_INPUT_CATEGORIES)
    suspend fun postInputCategories(@Header("Authorization") token: String,
                             @Body body: InputCategoriesRequest): Response<InputResponse>

    @POST(Values.PATH_INPUT)
    suspend fun postInput(@Header("Authorization") token: String,
                                    @Body body: InputRequest): Response<InputResponse>
}