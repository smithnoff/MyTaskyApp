package com.smithnoff.mytaskyapp.data.remote

import com.smithnoff.mytaskyapp.data.models.RegisterUserRequest
import com.smithnoff.mytaskyapp.data.models.UserLoginRequest
import com.smithnoff.mytaskyapp.data.models.UserLoginResponse
import com.smithnoff.mytaskyapp.utils.NetworkResult
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface TaskyApi {

    @POST("/register")
    fun registerUser(@Body userInfo: RegisterUserRequest): Response<Nothing>

    @POST("/login")
    suspend fun loginUser(@Body userInfo: UserLoginRequest): Response<UserLoginResponse>
}