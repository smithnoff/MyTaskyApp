package com.smithnoff.mytaskyapp.data.models

import com.google.gson.annotations.SerializedName

abstract class TaskyAgendaItem {
    @get:SerializedName("id")
    abstract val id: String

    @get:SerializedName("title")
    abstract val title: String

    @get:SerializedName("description")
    abstract val description: String?

    @get:SerializedName("remindAt")
    abstract val remindAt:Long

}