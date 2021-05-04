package com.codart.ipoteka.data.remote

import com.codart.ipoteka.data.entities.InputCategoriesRequest
import javax.inject.Inject

class UserDataRemoteDataSource  @Inject constructor(
        private val userDataService: UserDataService
): BaseDataSource() {

    suspend fun getUserData(token: String) = getResult { userDataService.getUserData(token) }

}