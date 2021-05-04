package com.codart.ipoteka.ui.main.profile

import androidx.lifecycle.ViewModel
import com.codart.ipoteka.data.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
        private val repository: AppRepository
): ViewModel() {
    fun getUserToken() = repository.getUserToken()

    fun getUserData(token: String)= repository.getUserData(token)

    fun sendFirebaseToken(token:String, firebaseToken: String)= repository.sendFirebaseToken(token, firebaseToken)


}