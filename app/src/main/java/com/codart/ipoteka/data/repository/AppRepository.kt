package com.codart.ipoteka.data.repository

import com.codart.ipoteka.data.entities.*
import javax.inject.Inject

class AppRepository @Inject constructor(
        private val authRepository: AuthRepository,
        private val inputRepository: InputRepository,
        private val userDataRepository: UserDataRepository,
        private val firebaseRepository: FirebaseRepository
){

    fun getToken()= authRepository.getToken()

    fun getNewToken() = authRepository.getNewToken()

    fun performLogin(token: String, telephone: String, password: String) = authRepository.performLogin(token, LoginRequest(telephone,password))

    suspend fun setUserToken(token: Token) = authRepository.setUserToken(token)

    fun getUserToken()= authRepository.getUserToken()

    fun getSizeOfTokens()= authRepository.getSizeOfTokens()

    fun postInputCategories(token: String, name: String, number: String) = inputRepository.postInputCategories(token, InputCategoriesRequest(name, number))

    fun postInput(token: String, surname: String, name: String,parents: String,birthday: String, code: String, number: String) = inputRepository.postInput(token, InputRequest(surname,name,parents,birthday,code,number))

    fun getUserData(token: String) = userDataRepository.getUserData(token)

    fun sendFirebaseToken(token: String, firebaseToken: String) = firebaseRepository.sendToken(token, FirebaseTokenRequest((firebaseToken)))

}