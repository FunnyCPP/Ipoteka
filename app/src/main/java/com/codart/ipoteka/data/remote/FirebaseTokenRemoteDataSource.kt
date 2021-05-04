package com.codart.ipoteka.data.remote

import com.codart.ipoteka.data.entities.FirebaseTokenRequest
import javax.inject.Inject

class FirebaseTokenRemoteDataSource@Inject constructor(
    private val tokenService: FirebaseTokenService
): BaseDataSource() {
    suspend fun sendToken(token: String,firebaseToken: FirebaseTokenRequest) = getResult { tokenService.sendFirebaseToken(token, firebaseToken) }

}