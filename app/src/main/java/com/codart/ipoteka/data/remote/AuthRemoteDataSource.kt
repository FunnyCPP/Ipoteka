package com.codart.ipoteka.data.remote

import com.codart.ipoteka.data.entities.LoginRequest
import javax.inject.Inject

class AuthRemoteDataSource @Inject constructor(
        private val authService: AuthService
): BaseDataSource() {
    suspend fun getToken() = getResult { authService.getToken() }

    suspend fun performLogin(token: String, body: LoginRequest) = getResult { authService.performLogin(token, body) }
}