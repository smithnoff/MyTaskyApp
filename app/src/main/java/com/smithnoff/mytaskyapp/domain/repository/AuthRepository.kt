package com.smithnoff.mytaskyapp.domain.repository

interface AuthRepository {
    suspend fun registerUser()
    suspend fun loginUser()
}