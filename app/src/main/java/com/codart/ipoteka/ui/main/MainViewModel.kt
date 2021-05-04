package com.codart.ipoteka.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.codart.ipoteka.data.entities.Token
import com.codart.ipoteka.data.local.TokenDao
import com.codart.ipoteka.data.local.UserTokenDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel@Inject constructor(
        private val userTokenDao: UserTokenDao
): ViewModel() {
    fun isLoggedIn(): LiveData<Token> = userTokenDao.getToken()
    companion object{
        var calculatorId = 1
    }
}