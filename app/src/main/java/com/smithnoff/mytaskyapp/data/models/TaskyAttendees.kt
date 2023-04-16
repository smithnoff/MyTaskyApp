package com.smithnoff.mytaskyapp.data.models

import com.google.gson.annotations.SerializedName

data class TaskyAttendees(
    @SerializedName("fullName") val fullName: String,
    @SerializedName("email") val email: String,
    @SerializedName("userId") val userId: String,
    @SerializedName("eventId") val eventId: String,
    @SerializedName("isGoing") val isGoing: Boolean,
    @SerializedName("remindAt") val remindAt: Long
)
