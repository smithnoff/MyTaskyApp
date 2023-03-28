package com.smithnoff.mytaskyapp.data.remote

import com.smithnoff.mytaskyapp.data.models.RegisterUserRequest
import com.smithnoff.mytaskyapp.data.models.UserLoginRequest
import com.smithnoff.mytaskyapp.data.models.UserLoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface TaskyApi {

    @POST("/register")
    suspend fun registerUser(@Body userInfo: RegisterUserRequest): Response<Unit>

    @POST("/login")
    suspend fun loginUser(@Body userInfo: UserLoginRequest): Response<UserLoginResponse>
}