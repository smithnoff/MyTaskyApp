package com.smithnoff.mytaskyapp.data.models

import com.google.gson.annotations.SerializedName

data class TaskyEvent(
override val id:String,
override val title:String,
override val description:String?,
override val remindAt:Long,
@SerializedName("from")val from:Long,
@SerializedName("to")val to:Long,
@SerializedName("host")val host:String,
@SerializedName("isUserEventCreator")val isUserEventCreator:Boolean,
@SerializedName("attendees")val attendees:TaskyAttendees,
@SerializedName("photos")val photos:TaskyPhoto
): TaskyAgendaItem()
