package com.codart.ipoteka.ui.main.input

import androidx.lifecycle.ViewModel
import com.codart.ipoteka.data.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InputViewModel @Inject constructor(
        private val repository: AppRepository
):ViewModel() {
    fun postInput(token: String, surname: String, name: String,parents: String,birthday: String, code: String, number: String) = repository.postInput(token, surname, name, parents, birthday, code, number)

    fun getToken() = repository.getToken()
}