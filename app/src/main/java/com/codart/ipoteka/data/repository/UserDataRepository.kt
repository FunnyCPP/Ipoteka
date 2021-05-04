package com.codart.ipoteka.data.repository

import com.codart.ipoteka.data.entities.InputCategoriesRequest
import com.codart.ipoteka.data.local.TokenDao
import com.codart.ipoteka.data.local.UserTokenDao
import com.codart.ipoteka.data.remote.AuthRemoteDataSource
import com.codart.ipoteka.data.remote.UserDataRemoteDataSource
import com.codart.ipoteka.utils.performGetOperationWithoutDB
import javax.inject.Inject

class UserDataRepository@Inject constructor(
        private val remoteDataSource: UserDataRemoteDataSource,
)  {
    fun getUserData(token: String) = performGetOperationWithoutDB { remoteDataSource.getUserData(token) }
}