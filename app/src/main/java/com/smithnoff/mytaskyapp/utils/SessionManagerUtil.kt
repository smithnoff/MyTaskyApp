package com.smithnoff.mytaskyapp.utils

import android.content.SharedPreferences
import com.smithnoff.mytaskyapp.data.models.UserLoginResponse
import javax.inject.Inject
import javax.inject.Singleton

const val USER_FULLNAME = "name"
const val USER_ID = "id"
const val USER_TOKEN = "token"

@Singleton
class SessionManagerUtil @Inject constructor(private val sharedPreferences: SharedPreferences) {

    fun startUserSession(user: UserLoginResponse) {
        with(sharedPreferences.edit()) {
            putString(USER_FULLNAME, user.fullName)
            putString(USER_ID, user.userId)
            putString(USER_TOKEN, user.token)
            apply()
        }
    }

    fun getSessionInfo() = UserLoginResponse(
        fullName = sharedPreferences.getString(USER_FULLNAME, "")!!,
        userId = sharedPreferences.getString(USER_ID, "")!!,
        token = sharedPreferences.getString(USER_TOKEN, "")!!
    )

    fun logoutUser() {
        sharedPreferences.edit().clear().apply()
    }

    fun getUserToken() = sharedPreferences.getString(USER_TOKEN, "")
}