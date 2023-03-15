package com.smithnoff.mytaskyapp.domain.repository

interface UserRepository {
    suspend fun registerUser()
    suspend fun loginUser()
}