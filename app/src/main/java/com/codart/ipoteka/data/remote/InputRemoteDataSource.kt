package com.codart.ipoteka.data.remote

import com.codart.ipoteka.data.entities.InputCategoriesRequest
import com.codart.ipoteka.data.entities.InputRequest
import com.codart.ipoteka.data.entities.LoginRequest
import javax.inject.Inject

class InputRemoteDataSource @Inject constructor(
        private val inputService: InputService
): BaseDataSource() {
    suspend fun postInputCategories(token: String, body: InputCategoriesRequest) = getResult { inputService.postInputCategories(token, body) }

    suspend fun postInput(token: String, body: InputRequest) =getResult { inputService.postInput(token, body) }

}