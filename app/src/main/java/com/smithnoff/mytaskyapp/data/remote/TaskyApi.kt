package com.smithnoff.mytaskyapp.data.remote

import retrofit2.http.POST

interface TaskyApi {

    @POST("/register")
    suspend fun registerUser()

    @POST("/login")
    suspend fun loginUser()
}