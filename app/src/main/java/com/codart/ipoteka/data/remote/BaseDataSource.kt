package com.codart.ipoteka.data.remote

import android.util.Log
import com.codart.ipoteka.utils.Resource
import retrofit2.Response
import timber.log.Timber

abstract class BaseDataSource {
    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                Log.d("Responce", "Success")
                val body = response.body()
                try {
                    if (body != null) return Resource.success(body)
                }
                catch (e: Exception)
                {
                    Log.e("Responce", e.toString())
                }
            }
            Log.e("Remote error", response.toString())
            Log.e("Remote error", response.raw().toString())
            return error(" ${response.code()} ${response.message()}",response.code())
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String,code: Int): Resource<T> {
        if(code==403)
        {
            return Resource.error("Неверный телефон или пароль")
        }
        return Resource.error("Network call has failed for a following reason: $message")
    }
}