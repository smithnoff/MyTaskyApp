package com.smithnoff.mytaskyapp.data.models

import com.google.gson.annotations.SerializedName

data class AgendaResponse(
    @SerializedName("events") val events: List<TaskyEvent>,
    @SerializedName("tasks") val tasks: List<TaskyTask>,
    @SerializedName("reminders") val reminders: List<TaskyReminder>
    )
