package com.smithnoff.mytaskyapp.data.models

import com.google.gson.annotations.SerializedName

data class TaskyReminder(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String?,
    @SerializedName("time") val time: Long,
    @SerializedName("remindAt") val remindAt: Long,
)