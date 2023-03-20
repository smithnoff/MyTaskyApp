package com.smithnoff.mytaskyapp.data.repository

import com.smithnoff.mytaskyapp.data.remote.TaskyApi
import com.smithnoff.mytaskyapp.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val api: TaskyApi) : AuthRepository {

    override suspend fun registerUser() {

    }

    override suspend fun loginUser() {

    }
}