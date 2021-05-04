package com.codart.ipoteka.data.repository

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.codart.ipoteka.data.entities.LoginRequest
import com.codart.ipoteka.data.entities.LoginResponse
import com.codart.ipoteka.data.entities.Token
import com.codart.ipoteka.data.entities.User
import com.codart.ipoteka.data.local.TokenDao
import com.codart.ipoteka.data.local.UserTokenDao
import com.codart.ipoteka.data.remote.AuthRemoteDataSource
import com.codart.ipoteka.utils.Resource
import com.codart.ipoteka.utils.performGetOperation
import com.codart.ipoteka.utils.performGetOperationWithoutDB
import com.codart.ipoteka.utils.performGetOperationWithoutGetFromDb
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.Exception
import javax.inject.Inject

class AuthRepository @Inject constructor(
        private val remoteDataSource: AuthRemoteDataSource,
        private val localDataSource: TokenDao,
        private val localUserDataSource: UserTokenDao
) {
    fun getToken(): LiveData<Resource<Token>>  {
        return if(getSizeOfTokens()!=0)
        {
            localDataSource.getToken().map { Resource(Resource.Status.SUCCESS,it,"") }
        }
        else {
            performGetOperationWithoutGetFromDb(
                    databaseQuery = { localDataSource.getToken() },
                    networkCall = { remoteDataSource.getToken() },
                    saveCallResult = { localDataSource.insert(it.data) })
        }
    }
    fun getNewToken() = performGetOperationWithoutGetFromDb(
            databaseQuery = { localDataSource.getToken() },
            networkCall = { remoteDataSource.getToken() },
            saveCallResult = { localDataSource.insert(it.data) })

    fun performLogin(token: String, body: LoginRequest) = performGetOperationWithoutDB { remoteDataSource.performLogin(token, body) }

    suspend fun setUserToken(token: Token) = localUserDataSource.insert(token)

    fun getUserToken() = localUserDataSource.getToken()

    fun getSizeOfTokens()= runBlocking{localDataSource.getSize()}

}
