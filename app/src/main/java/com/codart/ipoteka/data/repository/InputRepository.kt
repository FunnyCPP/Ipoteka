package com.codart.ipoteka.data.repository

import com.codart.ipoteka.data.entities.InputCategoriesRequest
import com.codart.ipoteka.data.entities.InputRequest
import com.codart.ipoteka.data.entities.LoginRequest
import com.codart.ipoteka.data.local.TokenDao
import com.codart.ipoteka.data.local.UserTokenDao
import com.codart.ipoteka.data.remote.AuthRemoteDataSource
import com.codart.ipoteka.data.remote.InputRemoteDataSource
import com.codart.ipoteka.utils.performGetOperationWithoutDB
import javax.inject.Inject

class InputRepository @Inject constructor(
        private val remoteDataSource: InputRemoteDataSource
) {
    fun postInputCategories(token: String, body: InputCategoriesRequest) = performGetOperationWithoutDB { remoteDataSource.postInputCategories(token, body) }

    fun postInput(token: String, body: InputRequest) = performGetOperationWithoutDB { remoteDataSource.postInput(token, body) }
}