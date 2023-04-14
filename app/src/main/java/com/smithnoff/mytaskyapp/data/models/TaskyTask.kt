package com.smithnoff.mytaskyapp.data.models

import com.google.gson.annotations.SerializedName

data class TaskyTask(
    override val id: String,
    override val title: String,
    override val description: String?,
    override val remindAt: Long,
    @SerializedName("time") val time: Long,
    @SerializedName("isDone") val isDone: Boolean
) : TaskyAgendaItem()
