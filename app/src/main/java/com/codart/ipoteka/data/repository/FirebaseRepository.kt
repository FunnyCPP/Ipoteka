package com.codart.ipoteka.data.repository

import com.codart.ipoteka.data.entities.FirebaseTokenRequest
import com.codart.ipoteka.data.remote.FirebaseTokenRemoteDataSource
import com.codart.ipoteka.data.remote.UserDataRemoteDataSource
import com.codart.ipoteka.utils.performGetOperationWithoutDB
import javax.inject.Inject

class FirebaseRepository @Inject constructor(
   private val remoteDataSource: FirebaseTokenRemoteDataSource
) {
    fun sendToken(token: String, firebaseToken: FirebaseTokenRequest) = performGetOperationWithoutDB { remoteDataSource.sendToken(token,firebaseToken) }
}