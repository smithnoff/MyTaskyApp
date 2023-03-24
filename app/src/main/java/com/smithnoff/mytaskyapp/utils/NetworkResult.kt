package com.smithnoff.mytaskyapp.utils

import retrofit2.Response

sealed class NetworkResult <T>(
        val data: T? = null,
        val message: String? = null
    ) {

        class Success<T>(data: T) : NetworkResult<T>(data = data)

        class Error<T>(errorMessage: String,data:T? = null) : NetworkResult<T>(message = errorMessage, data = data)

        class Loading<T>(val isLoading: Boolean = true): NetworkResult<T>(null)
}

fun <T> Response<T>.handleResponse():NetworkResult<T>{
    try {
        val response = this

        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                return NetworkResult.Success(body)
            }
        }

        else{
            return NetworkResult.Error(response.message())
        }

        return NetworkResult.Error("Something went wrong, try again")

    } catch (e: Exception) {
        return NetworkResult.Error("Something went wrong, $e")
    }
}