package com.smithnoff.mytaskyapp.data.models

import com.google.gson.annotations.SerializedName

data class TaskyPhoto (
    @SerializedName("key") val key: String,
    @SerializedName("url") val url: String
)
