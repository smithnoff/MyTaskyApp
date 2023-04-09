package com.smithnoff.mytaskyapp.data.models

import com.google.gson.annotations.SerializedName

data class TaskyEvent(
@SerializedName("id") val id:String,
@SerializedName("title")val title:String,
@SerializedName("description")val description:String?,
@SerializedName("from")val from:Long,
@SerializedName("to")val to:Long,
@SerializedName("remindAt")val remindAt:Long,
@SerializedName("host")val host:String,
@SerializedName("isUserEventCreator")val isUserEventCreator:Boolean,
@SerializedName("attendees")val attendees:TaskyAttendees,
@SerializedName("photos")val photos:TaskyPhoto
)
