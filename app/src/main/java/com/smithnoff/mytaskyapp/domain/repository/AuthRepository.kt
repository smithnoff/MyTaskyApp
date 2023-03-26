package com.smithnoff.mytaskyapp.domain.repository

import com.smithnoff.mytaskyapp.data.models.UserLoginRequest
import com.smithnoff.mytaskyapp.data.models.UserLoginResponse
import com.smithnoff.mytaskyapp.utils.Resource

interface AuthRepository {
    suspend fun registerUser()
    suspend fun loginUser(userInfo: UserLoginRequest):Resource<UserLoginResponse>
}