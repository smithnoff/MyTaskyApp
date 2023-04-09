package com.smithnoff.mytaskyapp.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

sealed class Resource <T>(
        val data: T? = null,
        val message: String? = null
    ) {

        class Success<T>(data: T) : Resource<T>(data = data)

        class Error<T>(errorMessage: String,data:T? = null) : Resource<T>(message = errorMessage, data = data)
}

suspend fun <T> safeApiCall(apiToBeCalled: suspend () -> Response<T>): Resource<T> {

    return withContext(Dispatchers.IO) {
        try {

            val response: Response<T> = apiToBeCalled()

            if (response.isSuccessful) {
                Resource.Success(data = response.body()!!)
            } else {

                val errorResponse = getErrorMessage(response.errorBody())
                Resource.Error(
                    errorMessage = errorResponse
                )
            }

        } catch (e: HttpException) {

            Resource.Error(errorMessage = e.message ?: "Something went wrong")
        } catch (e: IOException) {
            Resource.Error("Please check your network connection")
        } catch (e: Exception) {
            Resource.Error(errorMessage = "Something went wrong ${e.message}")
        }
    }
}

fun getErrorMessage(responseBody: ResponseBody?): String {
    return try {
        val jsonObject = JSONObject(responseBody!!.string())
        when {
            jsonObject.has("message") -> jsonObject.getString("message")
            else -> "Something wrong happened"
        }
    } catch (e: Exception) {
        "Something wrong happened"
    }
}