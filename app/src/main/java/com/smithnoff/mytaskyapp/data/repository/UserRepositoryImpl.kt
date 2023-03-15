package com.smithnoff.mytaskyapp.data.repository

import com.smithnoff.mytaskyapp.data.remote.TaskyApi
import com.smithnoff.mytaskyapp.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val api: TaskyApi) : UserRepository {

    override suspend fun registerUser() {

    }

    override suspend fun loginUser() {

    }
}