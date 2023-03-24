package com.smithnoff.mytaskyapp.data.repository

import com.smithnoff.mytaskyapp.data.models.UserLoginRequest
import com.smithnoff.mytaskyapp.data.models.UserLoginResponse
import com.smithnoff.mytaskyapp.data.remote.TaskyApi
import com.smithnoff.mytaskyapp.domain.repository.AuthRepository
import com.smithnoff.mytaskyapp.utils.NetworkResult
import com.smithnoff.mytaskyapp.utils.handleResponse
import java.util.ResourceBundle
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val api: TaskyApi) : AuthRepository {

    override suspend fun registerUser() {

    }

    override suspend fun loginUser(userInfo: UserLoginRequest):NetworkResult<UserLoginResponse> {
      return  api.loginUser(userInfo).handleResponse()
    }
}