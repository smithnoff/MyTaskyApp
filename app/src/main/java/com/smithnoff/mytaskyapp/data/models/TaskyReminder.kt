package com.smithnoff.mytaskyapp.data.models

import com.google.gson.annotations.SerializedName

data class TaskyReminder(
    override val id: String,
    override val title: String,
    override val description: String?,
    override val remindAt: Long,
    @SerializedName("time") val time: Long,
):TaskyAgendaItem()