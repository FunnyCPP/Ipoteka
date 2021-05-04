package com.codart.ipoteka.ui.main.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.codart.ipoteka.data.entities.LoginResponse
import com.codart.ipoteka.data.entities.Token
import com.codart.ipoteka.data.entities.User
import com.codart.ipoteka.data.repository.AppRepository
import com.codart.ipoteka.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class AuthViewModel@Inject constructor(
        val repository: AppRepository
) : ViewModel() {

    fun getToken()=repository.getToken()

    fun getNewToken()= repository.getNewToken()

    fun login(token: String, telephone: String, password: String)= repository.performLogin(token, telephone, password)

    fun setUserToken(token: Token){
        runBlocking {
            repository.setUserToken(token)
        }
    }

}