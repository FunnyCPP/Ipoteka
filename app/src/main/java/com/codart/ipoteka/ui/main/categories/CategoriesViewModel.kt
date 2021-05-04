package com.codart.ipoteka.ui.main.categories

import androidx.lifecycle.ViewModel
import com.codart.ipoteka.data.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel@Inject constructor(
        val repository: AppRepository
) : ViewModel() {

    fun postInput(token: String, name: String, number: String) = repository.postInputCategories(token, name, number)

    fun getToken()=repository.getToken()
}