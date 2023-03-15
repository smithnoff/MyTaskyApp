package com.smithnoff.mytaskyapp.data.remote

import com.smithnoff.mytaskyapp.utils.Constants.LOGIN
import com.smithnoff.mytaskyapp.utils.Constants.REGISTER
import retrofit2.http.POST

interface TaskyApi {

    @POST(REGISTER)
    suspend fun registerUser()

    @POST(LOGIN)
    suspend fun loginUser()
}