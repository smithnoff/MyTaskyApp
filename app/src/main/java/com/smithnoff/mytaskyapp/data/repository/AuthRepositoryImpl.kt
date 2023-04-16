package com.smithnoff.mytaskyapp.data.repository

import com.smithnoff.mytaskyapp.data.models.RegisterUserRequest
import com.smithnoff.mytaskyapp.data.models.UserLoginRequest
import com.smithnoff.mytaskyapp.data.models.UserLoginResponse
import com.smithnoff.mytaskyapp.data.remote.TaskyAuthApi
import com.smithnoff.mytaskyapp.domain.repository.AuthRepository
import com.smithnoff.mytaskyapp.utils.Resource
import com.smithnoff.mytaskyapp.utils.safeApiCall
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val api: TaskyAuthApi) : AuthRepository {

    override suspend fun registerUser(userInfo: RegisterUserRequest): Resource<Unit>{
        return  safeApiCall {  api.registerUser(userInfo) }
    }

    override suspend fun loginUser(userInfo: UserLoginRequest):Resource<UserLoginResponse> {
      return  safeApiCall {  api.loginUser(userInfo) }
    }
}