package com.smithnoff.mytaskyapp.data.models

import com.google.gson.annotations.SerializedName


data class UserLoginResponse (
    @SerializedName("fullName") val fullName: String,
    @SerializedName("userId") val userId: String,
    @SerializedName("token") val token: String
)
